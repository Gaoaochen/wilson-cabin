package com.example.guide.extend;

import com.example.guide.GuideApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
//@Order(value = -100000)
//@Configuration
public class ExcludeAutoConfigurationClass {

    public ExcludeAutoConfigurationClass() {
        System.out.println("-------------------------------------------");

        try {
            Class springbootApplication = GuideApplication.class;
            Annotation springBootApplication = springbootApplication.getAnnotation(SpringBootApplication.class);
//            Method method = springBootApplication.getClass().getMethod("exclude");
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(springBootApplication);
//            System.out.println(invocationHandler);
//            System.out.println(method.getClass());
            Field field = invocationHandler.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
//            Field methodField =invocationHandler.getClass().getDeclaredField("memberMethods");
//            System.out.println("methodField"+methodField);
//            methodField.setAccessible(true);
//            Object methodValue =methodField.get(invocationHandler);
//            if (methodValue instanceof Method[]){
//                System.out.println("methodValue instanceof Method[]");
//                Method[] methods =(Method[])methodValue;
//                for (Method method:methods){
//                    System.out.println("=========================="+method.getName());
//                }
//            }

            //LinkedHashMap实例
            Object memberValues = field.get(invocationHandler);
            System.out.println(memberValues.getClass().getName());
            //LinkedHashMap类
            Class linkedHashMapClass = memberValues.getClass();
            //node
            Class node = Class.forName("java.util.HashMap$Node");
            Class c = HashMap.class;
            //成员变量table数组
            Field tableField = c.getDeclaredField("table");
            tableField.setAccessible(true);
            //table数组实例
            Object[] objects = (Object[]) tableField.get(memberValues);

            for (Object object:objects){
                if (object!=null){
                    Field key =node.getDeclaredField("key");
                    Field value =node.getDeclaredField("value");
                    Field next =node.getDeclaredField("next");

                    key.setAccessible(true);
                    value.setAccessible(true);
                    next.setAccessible(true);

                    Object keyValue =key.get(object);
                    System.out.println("keyValue:"+keyValue);
                    Object valueValue =value.get(object);
                    System.out.println("valueValue:"+valueValue);
                    Object nextValue =next.get(object);
                    System.out.println("nextValue:"+nextValue);
                }
            }

//            String s = "123";
////            memberValues.put("exclude",s);
//            memberValues.get("exclude");
//            memberValues.put("exclude", DataSourceAutoConfiguration.class);
//            System.out.println(memberValues.get("excludeName"));
//            InvocationHandler invocationHandler= Proxy.getInvocationHandler(springbootApplication);
//            Field memberValuesField=invocationHandler.getClass().getDeclaredField("memberValues");
//            memberValuesField.setAccessible(true);
//            Map memberValues =(Map)memberValuesField.get(invocationHandler);
//            System.out.println(memberValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


