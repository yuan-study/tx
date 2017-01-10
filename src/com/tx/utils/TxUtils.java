package com.tx.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.BeansException;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zhyy on 2017/1/8.
 */
public class TxUtils implements ApplicationContextAware {
    private ApplicationContext act;
    ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
    ThreadLocal<Boolean> flag = new ThreadLocal<Boolean>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.act = applicationContext;
    }

    /**
     * 拦截所有的getConnection方法
     *
     * @param joinPoint
     * @return
     */
    public synchronized Object interConnection(ProceedingJoinPoint joinPoint) throws Throwable {
        Object conn = threadLocal.get();
        Boolean myflag = flag.get();
        if(!myflag){
            return  joinPoint.proceed();
        }
        if (null == conn) {
             Object obj = joinPoint.proceed();
            final Object myobj = obj;
            Callback callback = new MethodInterceptor() {
                @Override
                public Object intercept(Object proxyObject, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                    if(method.getName().equals("close")){
                        return null;
                    }else{
                        return method.invoke(myobj,args);
                    }
                }
            };
            Object enhanerObj = Enhancer.create(Connection.class, callback);
            threadLocal.set(enhanerObj);
            conn = obj;
        }
        return conn;
    }


    public synchronized Object interceptService(ProceedingJoinPoint joinPoint) {
        flag.set(true);
        Object obj = null;
        Connection conn = null;
        try {
            conn = act.getBean("dataSource", DataSource.class).getConnection();
            conn.setAutoCommit(false);
            obj = joinPoint.proceed(); // Service 中的 save()
            conn.commit();
        } catch (Throwable throwable) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwable.printStackTrace();
        } finally {
            flag.set(false);
            threadLocal.remove();
        }
        return obj;
    }


}
