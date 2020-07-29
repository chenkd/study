package com.chenkeda.cglib;

import com.chenkeda.cglib.pojo.Person;
import com.chenkeda.cglib.pojo.User;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDemo {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new MyIntercept());
//        enhancer.setInterceptDuringConstruction();
        enhancer.setSuperclass(User.class);
        User userProxy = (User) enhancer.create();
        userProxy.hello("world");
//        User user = new User("chenkeda", "world");

    }
}
class MyIntercept implements MethodInterceptor {

//    private User user;

//    public MyIntercept(User user) {
//        this.user = user;
//    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("---intercept before---");
//        Object result = method.invoke(user, args);
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("---intercept after---");
        return result;
    }
}
