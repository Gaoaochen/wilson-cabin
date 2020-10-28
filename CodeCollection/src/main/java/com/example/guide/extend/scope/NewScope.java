//package com.example.guide.extend.scope;
//
//import com.example.guide.extend.scope.imp.NewScopeImp;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//
//import java.lang.annotation.*;
//
//@Target({ElementType.TYPE, ElementType.METHOD})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Scope(NewScopeImp.SCOPE_NEW)
////自定义一个Scope
//public @interface NewScope {
//
//    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
//}
