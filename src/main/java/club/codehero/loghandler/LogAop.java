package club.codehero.loghandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CodeHero
 */
@Aspect
@Component
@Slf4j
public class LogAop {

    private RequestLog requestLog = new RequestLog();

    @Pointcut("execution(* club.codehero.controller.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        requestLog.setUrl(url);
        requestLog.setIp(ip);
        requestLog.setClassMethod(classMethod);
        requestLog.setArgs(args);

        log.info("Request : {}", requestLog);
    }

    @After("pointcut()")
    public void doAfter() {
        requestLog.setUrl(null);
        requestLog.setIp(null);
        requestLog.setArgs(null);
        requestLog.setClassMethod(null);
        //log.info("----------doAfter----------");
    }

    @AfterReturning(returning = "result", pointcut = "pointcut()")
    public void doAfterReturn(Object result) {
        log.info("Result : {}", result);

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class RequestLog {
        private String url;
        private String ip;
        private String ClassMethod;
        private Object[] args;


    }
}
