package spring.anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext container =new GenericXmlApplicationContext("annotest.xml");
        
        Foo f=container.getBean("makeFoo",Foo.class);
        f.doFoo();

        container.registerShutdownHook();
    }
}
