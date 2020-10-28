package com.example.guide.configuration;

import com.example.guide.extend.scope.imp.NewScopeImp;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ScopeConfig {

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        Map<String, Object> map = new HashMap<>();
        map.put("newScope", new NewScopeImp());
        customScopeConfigurer.setScopes(map);
        return customScopeConfigurer;
    }

//    @Bean
//    @Scope(value = "newScope", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public NewScopeImp NewScopeImpBean() {
//        NewScopeImp newScopeImp = NewScopeImp.getInstance();
//        System.out.println("=========================" + newScopeImp);
//        return newScopeImp;
//    }
}
