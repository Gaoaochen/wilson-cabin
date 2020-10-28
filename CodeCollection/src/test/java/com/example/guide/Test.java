package com.example.guide;

import com.example.guide.extend.scope.imp.NewScopeImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBeanFactory().registerScope(NewScopeImp.SCOPE_NEW, NewScopeImp.getInstance());
        context.refresh();


    }
}
