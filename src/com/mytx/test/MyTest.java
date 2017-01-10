package com.mytx.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * Created by zhyy on 2017/1/10.
 */
public class MyTest {
    @Test
    public void testTx() throws Exception {
        ApplicationContext act = new ClassPathXmlApplicationContext("com/mytx/mytx.xml");
        DataSource dataSource = act.getBean("dataSource", DataSource.class);

        /*
        * 用Spring内置的事务来写个测试！
        * */
//创建一个事务管理对象
        PlatformTransactionManager ptm = new DataSourceTransactionManager(dataSource);
//        声明事务模板
     /*   TransactionTemplate tt = new TransactionTemplate();
        tt.setTransactionManager(ptm);*/
        TransactionTemplate tt = new TransactionTemplate(ptm);

        tt.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                JdbcTemplate jt1 = new JdbcTemplate(dataSource);
                JdbcTemplate jt2 = new JdbcTemplate(dataSource);
                jt1.execute("insert into demo_info VALUES ('zhangsan')");
                jt2.execute("insert into demo_info VALUES ('lisisi)");
            }
        });


    }
}
