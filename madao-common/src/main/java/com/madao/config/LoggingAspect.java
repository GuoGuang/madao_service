package com.madao.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * 用于记录@Service、@Repository、@RestController的执行情况
 */
@Configuration
@Aspect
public class LoggingAspect {
	private final Environment env;

	public LoggingAspect(Environment env) {
		this.env = env;
	}

	/**
	 * 匹配所有@Service、@Repository、@RestController端点的切入点。
	 */
	@Pointcut(
			"within(@org.springframework.stereotype.Repository *)" +
					" || within(@org.springframework.stereotype.Service *)" +
					" || within(@org.springframework.web.bind.annotation.RestController *)"
	)
	public void springBeanPointcut() {
	}

	@Pointcut(
			"within(com.madao.*.dao..*)" +
					" || within(com.madao.*.service..*)" +
					" || within(com.madao.*.controller..*)"
	)
	public void applicationPackagePointcut() {
	}

	/**
	 * Retrieves the {@link Logger} associated to the given {@link JoinPoint}.
	 *
	 * @param joinPoint join point we want the logger for.
	 * @return {@link Logger} associated to the given {@link JoinPoint}.
	 */
	private Logger logger(JoinPoint joinPoint) {
		return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
	}

	/**
	 * 进入和退出方法时记录的建议。
	 *
	 * @param joinPoint join point for advice.
	 * @return result.
	 * @throws Throwable throws {@link IllegalArgumentException}.
	 */
	@Around("applicationPackagePointcut() && springBeanPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger log = logger(joinPoint);
		log.info("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		try {
			Object result = joinPoint.proceed();
			log.info("Exit: {}() with result = {}", joinPoint.getSignature().getName(), result);
			return result;
		} catch (IllegalArgumentException e) {
			log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
			throw e;
		}
	}
}
