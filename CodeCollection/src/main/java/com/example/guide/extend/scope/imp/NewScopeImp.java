package com.example.guide.extend.scope.imp;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.concurrent.ConcurrentHashMap;

public class NewScopeImp implements Scope {

    public static final String SCOPE_NEW = "reflesh";
    private static final NewScopeImp INSTANCE = new NewScopeImp();
    private ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public static NewScopeImp getInstance(){
        return INSTANCE;
    }

    public static void clean() {
        INSTANCE.beanMap.clear();
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
//        System.out.println("get方法执行：" +
//                name);
//        Object bean = beanMap.get(name);
//        if (bean == null) {
//            bean = objectFactory.getObject();
//            beanMap.put(name, bean);
//        }
        Object bean = objectFactory.getObject();
        return bean;
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
