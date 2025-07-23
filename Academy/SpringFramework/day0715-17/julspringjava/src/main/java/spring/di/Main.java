package spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //컨테이너 생성 및 설정 파일 수행
        AbstractApplicationContext container=new GenericXmlApplicationContext("ditest.xml");

        //빈을 가져와 메서드 실행
        Foo f=container.getBean("foo",Foo.class);
        f.doFoo();
        
        FooLookUp flu= container.getBean("fooLookUp",FooLookUp.class);
        flu.doFooLookUp();
        
        container.registerShutdownHook();//컨테이너 강제 종료        

     }
}
