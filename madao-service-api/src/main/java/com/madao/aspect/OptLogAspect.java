package com.madao.aspect;

import com.madao.annotation.OptLog;
import com.madao.api.base.OptLogServiceRpc;
import com.madao.enums.OptLogType;
import com.madao.utils.HttpServletUtil;
import com.madao.utils.JsonUtil;
import com.madao.utils.security.JWTAuthentication;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 操作日志切面
 * use: @OptLog(operationType = OptLogType.ADD, operationName = "注册用户")
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Aspect
@Component
public class OptLogAspect {

    @Autowired
    private OptLogServiceRpc optLogServiceRpc;

    /**
     * 切入点
     * 排除login方法
     */
    //@Pointcut("execution (* com.madao.*.controller..*.*(..)) && !execution(* com.madao.*.controller.UserController.login(..))  && !execution(* com.madao.*.controller.UserController.logout(..))")
    @Pointcut(value = "@annotation(com.madao.annotation.OptLog)")
    public void controllerAspect() {
        log.info("切入点签名");
    }

    /**
     * 拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    @SuppressWarnings("all")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null) {
            log.error("token为空，结束AOP前置通知");
            return;
        }
        Map<String, String> user = JWTAuthentication.parseJwtToClaims(JWTAuthentication.getFullAuthorization(token));
        String ipAddr = HttpServletUtil.getIpAddr(request);
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            OptLogType operationType = null;
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
            com.madao.model.pojo.base.OptLog log = new com.madao.model.pojo.base.OptLog();
            log.setClientIp(HttpServletUtil.getIpAddr(request));
            log.setUserId(user.get("id"));
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            log.setBrowser(userAgent.getBrowser().getName());
            log.setType(operationType);
            log.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            log.setOsInfo(userAgent.getOperatingSystem().getName());
            String argumentParam = "";
            for (Object argument : arguments) {
                if (!(argument instanceof ServletRequest)) {
                    argumentParam += JsonUtil.toJsonString(argument);
                }
            }
            optLogServiceRpc.insertOptLog(log);
        } catch (Exception e) {
            log.error("操作日志异常:{}", e.getMessage(), e);
        }
    }

    /**
     * 异常通知 用于拦截记录异常日志
     */
    @SuppressWarnings("all")
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        String ipAddr = HttpServletUtil.getIpAddr(request);
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            OptLogType operationType = null;
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

            log.error("=====异常通知开始=====");
            log.error("异常代码:" + e.getClass().getName());
            log.error("异常信息:" + e.getMessage());
            log.error("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + operationType);
            log.error("方法描述:" + operationName);
            log.error("请求人:" + "临时");
            log.error("请求IP:" + ipAddr);
            //==========数据库日志=========
            com.madao.model.pojo.base.OptLog log = new com.madao.model.pojo.base.OptLog();
            log.setClientIp(HttpServletUtil.getIpAddr(request));
            log.setUserId("临时用户");
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            log.setBrowser(userAgent.getBrowser().getName());
            log.setOsInfo(userAgent.getOperatingSystem().getName());
            log.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            log.setExceptionDetail(e.getClass().getName() + ":--->" + e.getMessage());
            String argumentParam = "";
            for (Object argument : arguments) {
                if (!(argument instanceof ServletRequest)) {
                    argumentParam += JsonUtil.toJsonString(argument);
                }
            }
            optLogServiceRpc.insertOptLog(log);
        } catch (Exception ex) {
	        log.error("异常方法:{}异常代码:{}异常信息:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage());
        }
    }
}