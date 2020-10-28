//package com.example.guide.task;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ConfigReadTask {
//
//    @Value("${enable.mybatis}")
//    String mybatis;
//
//    public String getMybatis() {
//        return mybatis;
//    }
//
//    public void setMybatis(String mybatis) {
//        this.mybatis = mybatis;
//    }
//
//
//
//    @Scheduled(fixedRate = 3000)
//    public void getConfig(){
//
//        System.out.println(getMybatis());
//    }
//
//}
