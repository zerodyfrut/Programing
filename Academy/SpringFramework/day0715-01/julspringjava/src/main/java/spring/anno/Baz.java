package spring.anno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
//bean으로 생성되기는 하지만, 직접 사용하는 것이 아니라
//bean 설정 정보를 담기 위한 클래스(@Bean과 함께 쓰임)
public class Baz {

    @Bean
    public Foo makeFoo(){
        Foo f= new Foo();
        System.out.println(f.str); //null
        f.str="aaa";
        return f;// 컨테이너에 등록
    }
}
