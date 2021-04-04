package site.heehee.samples.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    public static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     *   @GetMapping 설정된 메소드 또는 클래스 설정
     *   GetMapping 노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void GetMapping(){ }

    /**
     * @param joinPoint
     */
    @Before("GetMapping()")
    public void before(JoinPoint joinPoint) {
        logger.info("=====================AspectJ TEST  : Before Logging Start=====================");
        logger.info("=====================AspectJ TEST  : Before Logging End=====================");
    }

    /**
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        logger.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }


//    @Around("execution(* site..controller.*(..))")
    @Around("GetMapping()")
    public Object methodTimeLogger(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature ms = (MethodSignature)pjp.getSignature();

        String className = ms.getDeclaringType().getSimpleName();
        String methodName = ms.getName();

        StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        stopWatch.start();

        Object result = pjp.proceed();
        stopWatch.stop();

//        if (logger.isInfoEnabled()) {
            logger.info("Stop Watch : " + stopWatch.prettyPrint());
//        }

        return result;
    }

}
