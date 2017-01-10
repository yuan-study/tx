package com.cglib.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zhyy on 2017/1/8.
 */
public class PersonProxy implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object object, String s) throws BeansException {
        System.err.println("object is " + object);
        System.err.println("s is " + s);
        Callback callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object proxyObject, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                System.err.println("proxyObject is " + proxyObject.hashCode());
//                System.err.print(proxyObject);
                System.err.println("method is " + method.getName());
                System.err.println("methodProxy is " + methodProxy.getSuperName());
                Object obj =  methodProxy.invokeSuper(proxyObject,objects);
//                System.err.println("lan jie hou");
                return obj;
            }
        };
        Object enhObj = Enhancer.create(Person.class,callback);
        System.err.println("enhObj is " + enhObj);
        return enhObj;
    }
}
