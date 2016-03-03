package aspect;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logs {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void allResources() {
    }

    @Before("allResources()")
    public void adviceE(JoinPoint jp) {
        String log = jp.getSignature().getName() + " >>>";
        for (Object arg : jp.getArgs()) {
            log += "\n   ARG: " + arg;
        }
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("-----------------------------------");
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @AfterReturning(pointcut = "allResources()", returning = "result")
    public void adviceF(JoinPoint jp, Object result) {
        String log = "<<< " + jp.getSignature().getName() + ": return: " + result;
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @AfterThrowing(pointcut = "allResources()", throwing = "exception")
    public void consejoJ(JoinPoint jp, Exception exception) {
        String log = "<<< " + jp.getSignature().getName() + ": Exception: " + exception.getClass().getSimpleName();
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @Around("allResources()")
    public Object consejoE(ProceedingJoinPoint pjp) throws Throwable {
        Calendar before = Calendar.getInstance();
        Object obj = pjp.proceed();
        Calendar now = Calendar.getInstance();
        LogManager.getLogger(pjp.getSignature().getDeclaringTypeName()).info(
                "Processing time: " + (now.getTimeInMillis() - before.getTimeInMillis()) + "ms");
        return obj;
    }

}
