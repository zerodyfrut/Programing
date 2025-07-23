package spring.anno;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Qualifier("b")
public class Bar {

    public void doBar(){
        System.out.println("Bar.doBar() 실행 ");
    }

    @PostConstruct
    public void start(){
        System.out.println("Bar 초기화 메서드 : start");
    }

}
