package com.example.guide.controller;

import com.example.guide.entity.TestEntity;
import com.example.guide.extend.scope.imp.NewScopeImp;
import com.example.guide.service.DownLoadService;
import com.example.guide.service.SearchService;
import com.example.guide.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TController {

    @Autowired
    HttpServletResponse response;
    @Autowired
    DownLoadService downLoadService;
    @Autowired
    SearchService searchService;

    //    @RequestMapping("/log")
//    public String login(@RequestParam(name = "userName",required=false,defaultValue = "root") String inputUserName, @RequestParam(name = "passWord",required=false,defaultValue = "123456") String inputPassWord) {
//        System.out.println(inputUserName+"   "+inputPassWord);
//        String passWord = userDao.queryPassWord(inputUserName);
//        System.out.println(passWord);
//        if (passWord != null && passWord.equals(inputPassWord)){
//            System.out.println("用户："+inputUserName);
//            return "登陆成功";
//        }
//        else {
//            System.out.println("用户："+inputUserName);
//            return "登陆失败";
//        }
//    }
    //访问主页
    @RequestMapping("/")
    public String quickStart() {
        return "/file";
    }

    //下载功能，待完善
    @RequestMapping("/download")
    public void download(@RequestParam(name = "fileName") String fileName) {
        downLoadService.downLoad(response, fileName);
    }

    //该接口弃用
    @GetMapping("/fun")
    public String getParam(@RequestParam(name = "param", required = false) String param) throws IOException {
        searchService.setSearchParam(param);
        searchService.getSearchBound();
        searchService.search(searchService.path.get());
        searchService.setResponse(searchService.fileMap);
        System.out.println("===============================================");
        return "/success";
    }

    //清空数据库
    @RequestMapping("/clear")
    public String clearDataBase() {
        searchService.clearDatabase();
        return "/success";
    }

    //直接进行搜索，对cpu的消耗比较大
    @RequestMapping("/test")
    public String getParam() throws IOException {
        searchService.setSearchParam("java");
        searchService.getSearchBound();
        searchService.search(searchService.path.get());
        searchService.setResponse(searchService.fileMap);
        System.out.println("执行成功======================" + searchService.fileMap);
        return "/success";
    }

    //在数据库中搜索
    @RequestMapping("/test2")
    public String getParam2(@RequestParam(name = "param", required = false) String param) throws IOException {
        searchService.setSearchParam(param);
        searchService.getSearchBound();
        searchService.searchFromDatabase();
        System.out.println("执行成功======================" + searchService.fileMap);
        return "/success";
    }

    //带有缓存策略的搜索
    @RequestMapping("/test3")
    public String getParam3(@RequestParam(name = "param", required = false) String param) throws IOException {
        searchService.setSearchParam(param);
        searchService.getSearchBound();
        searchService.searchFromDatabase();
        System.out.println("执行成功======================" + searchService.fileMap);
        return "/success";
    }


// =====================================================以下为测试专用接口=================================================================

    //测试用
    @RequestMapping("/tes")
    public String quickStart2() {
//        searchService.test();
        PropertiesUtil p = new PropertiesUtil();
        System.out.println(p.getProperties("redis"));
        return "success";
    }

    //获取请求头
    @RequestMapping("/getHeaders")
    public String quickStart3(@RequestHeader Map<String, String> headers) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String key : headers.keySet()) {
            System.out.println("key:" + key + "   " + "value:" + headers.get(key));
        }
        System.out.println("===========================================================");
        return "/success";
    }

    //    @Autowired
//    NewScopeImp newScopeImp;
    @RequestMapping("/t")
    public String quickStart3() {
        searchService.efficiencyTest();
        return "success";
    }
}