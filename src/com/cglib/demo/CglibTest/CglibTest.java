package com.cglib.demo.CglibTest;

import com.cglib.demo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhyy on 2017/1/8.
 */
public class CglibTest {
    @Test
    public void testCglib() throws Exception{
        ApplicationContext act= new ClassPathXmlApplicationContext("applicationContext.xml");
        Person p = act.getBean("person",Person.class);
        System.err.println("p is " + p);
        p.run();
    }
}
