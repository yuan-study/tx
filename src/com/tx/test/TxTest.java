package com.tx.test;

import com.tx.myself.Action;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by zhyy on 2017/1/7.
 */
public class TxTest {

    public static void main(String[] args){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        final Action action = act.getBean("action", Action.class);
        for (int i = 0 ;i < 10;i++){
            System.err.println("i =" + i);
            new Thread(){
                public void run(){
                    action.execute();
                }
            }.start();
        }


    }

    @Test
    public void testTx01() throws Exception{
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");

       final Action action = act.getBean("action", Action.class);

        action.execute();
        for (int i = 0 ;i < 10;i++){
            System.err.println("i =" + i);
            new Thread(){
                public void run(){
                    action.execute();
                }
            }.start();
        }

    }


}
