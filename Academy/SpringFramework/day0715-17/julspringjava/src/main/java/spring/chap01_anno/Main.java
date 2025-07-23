package spring.chap01_anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext beanFactory 
		= new GenericXmlApplicationContext("applicationContext2.xml");
		WriteArticleService articleService 
		= (WriteArticleService) beanFactory.getBean("writeArticleService");
		articleService.write(new Article());
		
	}
}