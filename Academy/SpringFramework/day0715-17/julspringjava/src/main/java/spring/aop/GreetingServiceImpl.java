package spring.aop;
//공통 로직 호출 코드 없음.

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("greeting")
public class GreetingServiceImpl implements GreetingService {

    @Value("안녕")
    String greeting;

    @Override
    public void sayHello(String name) {
        System.out.println("sayHello : " + greeting + " : " + name);
    }

    // @Override
    public void sayBye(String name) throws Exception {
        System.out.println("sayBye : " + greeting + " : " + name);
        throw new Exception("강제 예외 발생");
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
