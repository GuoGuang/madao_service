package com.madao.utils.thread;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.madao.exception.custom.ThreadException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;


/**
 * 业务线程池
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-05-23 07:37
 */
@Slf4j
@Getter
public class BusinessThreadExecutor {

	// 获取默认构造的通用线程池，线程池核心是为 CPU 核数，最大线程数为 8倍 CPU 核数
	private static final ThreadPoolExecutor BASE_EXECUTOR;

	static {
		String executorName = "BaseExecutor";
		BASE_EXECUTOR = buildThreadFirstExecutor(executorName);
		ExecutorManager.registerThreadPoolExecutor(executorName, BASE_EXECUTOR);
	}

	/**
	 * 获取默认线程数的线程池
	 * 内部基于无界队列，请求量很大时候可能导致OOM的发生
	 * @param poolName 线程池名称
	 * @param nThreads 线程数量
	 * @param uncaughtExceptionHandler 异常回调
	 * @return ExecutorService
	 */
	public static ExecutorService newFixedThreadPool(String poolName,int nThreads,Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
		ThreadPoolExecutor executor =  new ThreadPoolExecutor(nThreads, nThreads,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(),
				new ThreadFactoryBuilder()
						.setUncaughtExceptionHandler(uncaughtExceptionHandler)
						.setNameFormat(poolName + "-%d").build());

		BusinessThreadExecutor.displayThreadPoolStatus(executor, poolName);
		BusinessThreadExecutor.hookShutdownThreadPool(executor, poolName);
		ExecutorManager.registerThreadPoolExecutor(poolName, executor);
		return executor;
	}

	/**
	 * 构建线程优先的线程池
	 * <p>
	 * 线程池默认是当核心线程数满了后，将任务添加到工作队列中，当工作队列满了之后，再创建线程直到达到最大线程数。
	 *
	 * <p>
	 * 线程优先的线程池，就是在核心线程满了之后，继续创建线程，直到达到最大线程数之后，再把任务添加到工作队列中。
	 *
	 * <p>
	 * 此方法默认设置核心线程数为 CPU 核数，最大线程数为 8倍 CPU 核数，空闲线程超过 5 分钟销毁，工作队列大小为 65536。
	 *
	 * @param poolName 线程池名称
	 * @return ThreadPoolExecutor
	 */
	public static ThreadPoolExecutor buildThreadFirstExecutor(String poolName) {
		int coreSize = BusinessThreadExecutor.getCpuProcessors();
		int maxSize = coreSize * 8;
		return buildThreadFirstExecutor(coreSize, maxSize, 5, TimeUnit.MINUTES, 1 << 16, poolName);
	}


	/**
	 * 构建线程优先的线程池，把队列当成一个后备方案
	 * - 线程池默认是当核心线程数满了后，将任务添加到工作队列中，当工作队列满了之后，再创建线程直到达到最大线程数。
	 * - 而线程优先的线程池，就是在核心线程满了之后，继续创建线程，直到达到最大线程数之后，再把任务添加到工作队列中。
	 *
	 * @param corePoolSize    核心线程数
	 * @param maximumPoolSize 最大线程数
	 * @param keepAliveTime   空闲线程的空闲时间
	 * @param unit            时间单位
	 * @param workQueueSize   工作队列容量大小
	 * @param poolName        线程池名称
	 * @return ThreadPoolExecutor
	 */
	public static ThreadPoolExecutor buildThreadFirstExecutor(int corePoolSize,
	                                                          int maximumPoolSize,
	                                                          long keepAliveTime,
	                                                          TimeUnit unit,
	                                                          int workQueueSize,
	                                                          String poolName) {
		// 自定义队列，优先开启更多线程，而不是放入队列
		// 通过重写队列的 offer 方法，直接返回 false，造成这个队列已满的假象，线程池在工作队列满了无法入队的情况下会扩容线程池。
		// 直到线程数达到最大线程数，就会触发拒绝策略，此时再通过自定义的拒绝策略将任务通过队列的 put 方法放入队列中。
		// 这样就可以优先开启更多线程，而不是进入队列了。
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(workQueueSize) {

			@Override
			public boolean offer(@Nonnull Runnable o) {
				return false; // 造成队列已满的假象
			}
		};

		// 当线程达到 maximumPoolSize 时会触发拒绝策略，此时将任务 put 到队列中
		RejectedExecutionHandler rejectedExecutionHandler = (runnable, executor) -> {
			try {
				// 任务拒绝时，通过 offer 放入队列
				queue.put(runnable);
			} catch (InterruptedException e) {
				log.error("{} 插入队列被打断. ", poolName, e);
				Thread.currentThread().interrupt();
			}
		};

		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, unit,
				queue,
				new ThreadFactoryBuilder()
						.setNameFormat(poolName + "-%d")
						.setUncaughtExceptionHandler((Thread thread, Throwable throwable) -> {
							log.error("{} 线程池未捕获的异常, ThreadName: [{}]", poolName, thread.toString(), throwable);
						})
						.build(), rejectedExecutionHandler
		);

		executor.allowCoreThreadTimeOut(true);

		BusinessThreadExecutor.displayThreadPoolStatus(executor, poolName);
		BusinessThreadExecutor.hookShutdownThreadPool(executor, poolName);

		ExecutorManager.registerThreadPoolExecutor(poolName, executor);

		return executor;
	}

	/**
	 * 批量提交异步任务，使用默认的线程池
	 *
	 * @param tasks 将任务转化为 AsyncTask 批量提交
	 */
	public static <T> List<T> batchExecuteAsync(List<AsyncTask<T>> tasks, @Nonnull String taskName) {
		return batchExecuteAsync(tasks, BASE_EXECUTOR, taskName);
	}

	/**
	 * 批量提交异步任务，执行失败可抛出异常或返回异常编码即可 <br>
	 * <p>
	 * 需注意提交的异步任务无法控制事务，一般需容忍产生一些垃圾数据的情况下才能使用异步任务，异步任务执行失败将抛出异常，主线程可回滚事务.
	 * <p>
	 * 异步任务失败后，将取消剩余的任务执行.
	 *
	 * @param tasks    将任务转化为 AsyncTask 批量提交
	 * @param executor 线程池，需自行根据业务场景创建相应的线程池
	 * @return 返回执行结果
	 */
	public static <T> List<T> batchExecuteAsync(@Nonnull List<AsyncTask<T>> tasks, @Nonnull ThreadPoolExecutor executor, @Nonnull String taskName) {
		if (CollectionUtils.isEmpty(tasks)) {
			return Collections.emptyList();
		}
		int size = tasks.size();
		List<Callable<T>> callables = tasks.stream().map(t -> (Callable<T>) () -> {
			try {
				T r = t.doExecute();
				log.info("[>>========线程Executor======<<] 异步任务执行成功. 线程名称: [{}], 批处理任务名称: [{}], 子任务名称: [{}]",
						Thread.currentThread().getName(), taskName, t.taskName());
				return r;
			} catch (Throwable e) {
				log.error("[>>========线程Executor======<<] 异步任务执行错误. 线程名称: [{}], 批处理任务名称: [{}], 子任务名称: [{}], 异常: {}",
						Thread.currentThread().getName(), taskName, t.taskName(), e.getMessage());
				throw e;
			}
		}).toList();

		CompletionService<T> cs = new ExecutorCompletionService<>(executor, new LinkedBlockingQueue<>(size));
		List<Future<T>> futures = new ArrayList<>(size);
		log.info("[>>========线程Executor======<<] 启动异步任务, 批处理任务名称: [{}], 任务数量: [{}]", taskName, size);

		for (Callable<T> task : callables) {
			futures.add(cs.submit(task));
		}

		List<T> resultList = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			try {
				Future<T> future = cs.poll(6, TimeUnit.MINUTES);
				if (future != null) {
					T result = future.get();
					resultList.add(result);
					log.debug("[>>========线程Executor======<<] 异步任务 [{}] - [{}]执行成功，结果: {}", taskName, i, result);
				} else {
					cancelTask(futures);
					log.error("[>>========线程Executor======<<] 异步任务 [{}] - [{}] 执行超时，然后取消其他任务.", taskName, i);
					throw new ThreadException("error.timeout");
				}
			} catch (ExecutionException e) {
				log.warn("[>>========线程Executor======<<] 异步任务 [{}] - [{}] 执行错误，然后取消其他任务", taskName, i, e);
				cancelTask(futures);
				Throwable throwable = e.getCause();
				if (throwable instanceof DuplicateKeyException duplicateKeyException) {
					throw duplicateKeyException;
				} else {
					throw new ThreadException("error.executorError" + e.getCause().getMessage());
				}
			} catch (InterruptedException e) {
				cancelTask(futures);
				Thread.currentThread().interrupt(); // 重置中断标识
				log.error("[>>========线程Executor======<<] 异步任务 [{}] - [{}] 被打断.", taskName, i);
				throw new ThreadException("error.timeout");
			}
		}
		log.info("[>>========线程Executor======<<]  完成异步任务，批处理任务名称: [{}], TaskSize: [{}]", taskName, size);
		return resultList;
	}

	/**
	 * 根据一定周期输出线程池的状态
	 *
	 * @param threadPool     线程池
	 * @param threadPoolName 线程池名称
	 */
	public static void displayThreadPoolStatus(ThreadPoolExecutor threadPool, String threadPoolName) {
		displayThreadPoolStatus(threadPool, threadPoolName, RandomUtils.nextInt(60, 600), TimeUnit.SECONDS);
	}

	/**
	 * 根据一定周期输出线程池的状态
	 *
	 * @param threadPool     线程池
	 * @param threadPoolName 线程池名称
	 * @param period         周期
	 * @param unit           时间单位
	 */
	public static void displayThreadPoolStatus(ThreadPoolExecutor threadPool, String threadPoolName, long period, TimeUnit unit) {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
			String payload = "[>>========线程池状态======<<] 线程池名称: [{}], 池状态: [shutdown={}, Terminated={}], 线程池线程数量: {}, 线程最大达到的数量: {}, 工作线程数: {}, 总任务数: {}, 已完成的任务数: {}, 队列中的任务: {}";
			Object[] params = new Object[]{threadPoolName,
					threadPool.isShutdown(), threadPool.isTerminated(), // 线程是否被终止
					threadPool.getPoolSize(), // 线程池线程数量
					threadPool.getLargestPoolSize(), // 线程最大达到的数量
					threadPool.getActiveCount(), // 工作线程数
					threadPool.getTaskCount(), // 总任务数
					threadPool.getCompletedTaskCount(), // 已完成的任务数
					threadPool.getQueue().size()};

			if (threadPool.getQueue().remainingCapacity() < 64) {
				log.warn(payload, params);
			} else {
				log.info(payload, params);
			}
		}, 0, period, unit);
	}

	/**
	 * 添加Hook在Jvm关闭时优雅的关闭线程池
	 *
	 * @param threadPool     线程池
	 * @param threadPoolName 线程池名称
	 */
	public static void hookShutdownThreadPool(ExecutorService threadPool, String threadPoolName) {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			log.info("[>>ExecutorShutdown<<] 开始关闭线程池: [{}]", threadPoolName);
			// 使新任务无法提交
			threadPool.shutdown();
			try {
				// 等待未完成任务结束
				if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
					threadPool.shutdownNow();
					log.warn("[>>ExecutorShutdown<<]取消当前执行的任务，可能会导致一些任务不一致。请查看日志.");

					// 等待任务取消的响应
					if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
						log.error("[>>ExecutorShutdown<<] 即使中断工作线程也无法关闭线程池，这可能会导致某些任务不一致。请查看日志");
					}
				}
			} catch (InterruptedException ie) {
				// 重新取消当前线程进行中断
				threadPool.shutdownNow();
				log.error("[>>ExecutorShutdown<<] 当前服务器线程在尝试停止工作线程时被中断。这可能导致不一致的状态。请查看日志");
				// 保留中断状态
				Thread.currentThread().interrupt();
			}
			log.info("[>>ExecutorShutdown<<] 最后关闭线程池: [{}]", threadPoolName);
		}));
	}

	/**
	 * 获取返回CPU核数
	 *
	 * @return 返回CPU核数，默认为8
	 */
	public static int getCpuProcessors() {
		return Runtime.getRuntime() != null && Runtime.getRuntime().availableProcessors() > 0 ?
				Runtime.getRuntime().availableProcessors() : 8;
	}

	/**
	 * 取消任务
	 *
	 * @param futures 任务列表
	 */
	private static <T> void cancelTask(List<Future<T>> futures) {
		for (Future<T> future : futures) {
			if (!future.isDone()) {
				future.cancel(true);
			}
		}
	}

	/**
	 * 提交一组无返回值的任务，其中一个失败则全部失败。
	 * @param taskList 任务列表
	 * @param action 完成时的操作
	 * @param supplier 待执行的业务
	 * @param executorService 业务独有线程池，粒度不可过大或过小，可以以功能模块为维护进行划分，根据流程再细分。
	 */
	public static void main(List<Integer> taskList, BiConsumer<Object, ? super Throwable> action, Supplier<Object> supplier,ExecutorService executorService) {
			CompletableFuture<?>[] cfs = taskList.stream()
					.map(prescriptionId -> CompletableFuture.supplyAsync(supplier,executorService)
					).toArray(CompletableFuture[]::new);
			anyFail(cfs).whenComplete(action);
	}

	/**
	 * 任意一个失败，则算全部失败
	 */
	private static CompletableFuture<?> anyFail(CompletableFuture... futures) {
		CompletableFuture<?> allComplete = CompletableFuture.allOf(futures);
		CompletableFuture<?> anyException = new CompletableFuture<>();
		for (CompletableFuture<?> completableFuture : futures) {
			completableFuture.exceptionally((t) -> {
				anyException.completeExceptionally(t);
				return null;
			});
		}
		return CompletableFuture.anyOf(allComplete, anyException);
	}


	public static class ExecutorManager {

		private static final ConcurrentHashMap<String, ThreadPoolExecutor> EXECUTORS = new ConcurrentHashMap<>(8);

		/**
		 * 向管理器注册线程池
		 *
		 * @param threadPoolName 线程池名称
		 * @param executor       ThreadPoolExecutor
		 */
		public static void registerThreadPoolExecutor(String threadPoolName, ThreadPoolExecutor executor) {
			EXECUTORS.put(threadPoolName, executor);
		}

		/**
		 * 根据名称获取线程池
		 *
		 * @param threadPoolName 线程池名称
		 */
		public static ThreadPoolExecutor getThreadPoolExecutor(String threadPoolName) {
			return EXECUTORS.get(threadPoolName);
		}

		/**
		 * 获取所有已注册的线程池
		 *
		 * @return ThreadPoolExecutor
		 */
		public static Map<String, ThreadPoolExecutor> getAllThreadPoolExecutor() {
			return ImmutableMap.copyOf(EXECUTORS);
		}

		/**
		 * 根据名称移除已注册的线程池
		 *
		 * @param threadPoolName 线程池名称
		 */
		public static void removeThreadPoolExecutor(String threadPoolName) {
			EXECUTORS.remove(threadPoolName);
		}
	}

}
