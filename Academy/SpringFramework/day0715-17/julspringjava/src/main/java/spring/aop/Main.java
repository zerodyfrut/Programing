package spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext container=new GenericXmlApplicationContext("aoptest.xml");

        GreetingService greeting=container.getBean("greeting",GreetingService.class);
        greeting.sayHello("김스프링"); //핵심 로직 메서드 호출
        
        // try {
        //     greeting.sayBye("김스프링");
        // } catch (Exception e) {
        //     System.out.println("sayBye 예외 처리 완료");
        // } //핵심 로직 메서드 호출
        

    }
}