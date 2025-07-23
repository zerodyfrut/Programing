package spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {

    @Pointcut("execution(* spring.aop.GreetingService.*(..))")
    private void pointcut(){}

    @Around("pointcut()")
    public Object timeCheck(ProceedingJoinPoint joinPoint){
        
        Signature s= joinPoint.getSignature();
        String methodName =s.toLongString(); //실제 호출한 메서드 이름
        
        long start = System.nanoTime();
        System.out.println("METHOD Before : "+methodName+" time check start");
        Object obj=null;

        try {
            obj= joinPoint.proceed();//핵심 로직 메서드 실행, 
                                     //끝나면 돌아와 남은 작엄 수행
        } catch (Throwable e) {
            System.out.println("METHOD Error : "+ methodName);
        }
        
        long end=System.nanoTime();
        System.out.println("METHOD After : "+methodName+" time check end");
        System.out.println("METHOD "+methodName +" processing time is "+(end-start)+"ns");
    
        return obj;
    }
}
