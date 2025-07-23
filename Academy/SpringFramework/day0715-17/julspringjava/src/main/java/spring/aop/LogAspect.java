package spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class LogAspect {

    @Pointcut("execution(public * sayHello(..))")
    private void pointcut(){} //아무일도 하지않는 메소드 , 포인트 컷을 만드는 용도
                              //메소드 이름으로 포인트컷을 가리킴. 여기서 밖에 못씀

    @Before("pointcut()")
    public void beforeLogging() {
        System.out.println("***메서드 호출 전***");
    }

    
    @AfterReturning(pointcut = "pointcut()", returning = "returnValue")
    public void afterLogging(Object returnValue) {
        System.out.println("***메서드 호출 후***");
    }

    @AfterThrowing(pointcut = "pointcut()", throwing="e")
    public void throwingLogging(Exception e) {
        System.out.println("***예외 발생 : " + e.getMessage() + "***");
    }

    @After("execution(* *Bye(..))")
    public void alwayLogging() {
        System.out.println("***항상 실행***");
    }
}
