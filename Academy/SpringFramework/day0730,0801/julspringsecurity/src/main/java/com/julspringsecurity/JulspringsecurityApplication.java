package com.julspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class JulspringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JulspringsecurityApplication.class, args);
	}

  @Bean
   HiddenHttpMethodFilter hiddenHttpMethodFilter(){
      return new HiddenHttpMethodFilter();
   }
}
