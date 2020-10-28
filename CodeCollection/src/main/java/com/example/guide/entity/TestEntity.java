package com.example.guide.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "newScope", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TestEntity {

    @Value("${enable.name}")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "TestEntity{" +
//                "name='" + name + '\'' +
//                '}';
//    }
}
