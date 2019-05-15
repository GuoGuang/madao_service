package com.youyd.aspect;

import com.youyd.annotation.OptLog;
import com.youyd.api.base.OptLogServiceRpc;
import com.youyd.pojo.user.User;
import com.youyd.utils.DateUtil;
import com.youyd.utils.HttpServletUtil;
import com.youyd.utils.JsonUtil;
import com.youyd.utils.LogBack;
import com.youyd.utils.security.JWTAuthentication;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 * 操作日志切点类
 * https://blog.csdn.net/m0_37556444/article/details/83791651
 * @author : LGG
 * @create : 2018-09-27
 **/

@Aspect
@Component
public class OptLogAspect {

	@Autowired
	private OptLogServiceRpc optLogServiceRpc;

	@Autowired
	private JWTAuthentication jwtAuthentication;

	/**
	 * 切入点
	 * 排除login方法
	 */
	//@Pointcut("execution (* com.youyd.*.controller..*.*(..)) && !execution(* com.youyd.*.controller.UserController.login(..))  && !execution(* com.youyd.*.controller.UserController.logout(..))")
	@Pointcut(value="@annotation(com.youyd.annotation.OptLog)")
	public void controllerAspect() {
	}
	private static final ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * @param joinPoint 切点
	 * @author ： LGG
	 * @date :2019年5月4日12:41:30
	 */
	@Before("controllerAspect()")
	@SuppressWarnings("all")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		//读取token
		/*SysUser user = (SysUser) session.getAttribute("user");
		if(user==null){
			user=new SysUser();
			user.setUserName("非注册用户");
		}*/
		//请求的IP
		final String token = request.getHeader("X-Token");
		Claims claims = jwtAuthentication.parseJWT(token);
		User user = (User)JsonUtil.jsonToPojo(claims.get("sub").toString(),User.class);
		String ipAddr = HttpServletUtil.getIpAddr(request);
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			Integer operationType = 0;
			String operationName = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						operationType = method.getAnnotation(OptLog.class).operationType();
						operationName = method.getAnnotation(OptLog.class).operationName();
						break;
					}
				}
			}
			//*========控制台输出=========*//
			System.out.println("=====controller前置通知开始=====");
			System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
			System.out.println("方法描述:" + operationName);
			System.out.println("请求人:" + "aaaa");
			System.out.println("请求IP:" + ipAddr);
			//*========数据库日志=========*//
			com.youyd.pojo.base.OptLog log = new com.youyd.pojo.base.OptLog();
			log.setClientIp(HttpServletUtil.getIpAddr(request));
			log.setUserId(user.getId());
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			log.setBrowser(userAgent.getBrowser().getName());
			log.setType(operationType);
			log.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
			log.setOsInfo(userAgent.getOperatingSystem().getName());
			log.setParams(JsonUtil.toJsonString(arguments));
			log.setCreateAt(DateUtil.getTimestamp());
			log.setUpdateAt(DateUtil.getTimestamp());

			/*log.setDescription(operationName);
			log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
			log.setLogType(0);
			log.setRequestIp(ip);
			log.setExceptionCode(null);
			log.setExceptionDetail( null);
			log.setParams( null);
			log.setCreateBy(user.getUserName());
			log.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			log.setRequestIp(ip);*/
			//保存数据库
			optLogServiceRpc.insertOptLog(log);
			System.out.println("=====controller前置通知结束=====");
		} catch (Exception e) {
			//记录本地异常日志
			LogBack.error("==前置通知异常==");
			LogBack.error("异常信息:{}", e.getMessage(),e);
		}


	}





	/**
	 * 异常通知 用于拦截记录异常日志
	 * @param joinPoint 切点
	 * @author ： LGG
	 * @date :2019年5月4日12:41:30
	 */
	@SuppressWarnings("all")
	@AfterThrowing(pointcut = "controllerAspect()", throwing="e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		//读取session中的用户
		/*SysUser user = (SysUser) session.getAttribute("user");
		if(user==null){
			user=new SysUser();
			user.setUserName("非注册用户");
		}*/

		String ipAddr = HttpServletUtil.getIpAddr(request);
		try {

			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			int operationType = 0;
			String operationName = "";
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						operationType = method.getAnnotation(OptLog.class).operationType();
						operationName = method.getAnnotation(OptLog.class).operationName();
						break;
					}
				}
			}
			/*========控制台输出=========*/
			System.out.println("=====异常通知开始=====");
			System.out.println("异常代码:" + e.getClass().getName());
			System.out.println("异常信息:" + e.getMessage());
			System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
			System.out.println("方法描述:" + operationName);
			System.out.println("请求人:" + "临时");
			System.out.println("请求IP:" + ipAddr);
			//==========数据库日志=========
			com.youyd.pojo.base.OptLog log = new com.youyd.pojo.base.OptLog();
			log.setClientIp(HttpServletUtil.getIpAddr(request));
			log.setUserId("临时用户");
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			log.setBrowser(userAgent.getBrowser().getName());
			log.setOsInfo(userAgent.getOperatingSystem().getName());
			log.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
			log.setExceptionDetail(e.getClass().getName() + ":--->" +e.getMessage());
			log.setParams(JsonUtil.toJsonString(arguments));
			log.setCreateAt(DateUtil.getTimestamp());
			log.setUpdateAt(DateUtil.getTimestamp());
			/*SysLog log = new SysLog();
			log.setDescription(operationName);
			log.setExceptionCode(e.getClass().getName());
			log.setLogType(1);
			log.setExceptionDetail(e.getMessage());
			log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			log.setParams(params);
			log.setCreateBy(user.getUserName());
			log.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			log.setRequestIp(ip);*/
			//保存数据库
			optLogServiceRpc.insertOptLog(log);
			System.out.println("=====异常通知结束=====");
		} catch (Exception ex) {
			//记录本地异常日志
			LogBack.error("==异常通知异常==");
			LogBack.error("异常信息:{}", ex.getMessage(),ex);
		}
		//==========记录本地异常日志==========
		LogBack.error("异常方法:{}异常代码:{}异常信息:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage());

	}

}