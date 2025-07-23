package spring.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;

@Component("myFoo")
public class Foo {

    //@Resource
    @Autowired
    @Qualifier("b")
    Bar bar;

    @Value("Hello")//기본형, 문자열에 값을 저장
    String str;
    
    public void doFoo(){
        System.out.println("Foo.doFoo() 실행");
        bar.doBar();
        System.out.println("str : "+ str);
    }

    @PreDestroy
    public void stop(){
        System.out.println("Foo 소멸 메소드 : stop");
    }
}
