package com.example.julspringweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
// 자신과 하위패키지에 Scan(자동으로 @component 같은거 실행해서 컨테이너 및 bean 생성)
// 위치를 따로 추가 지정할 수 도 있음.
public class JulspringwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JulspringwebApplication.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}// _method라는 이름으로 전달된 값을 요청방식으로 인식한다.

}
