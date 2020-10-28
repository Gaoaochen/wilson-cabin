package com.example.guide.service;

import com.alibaba.fastjson.JSON;
import com.example.guide.dao.SearchMapper;
import com.example.guide.entity.FileEntity;
import com.example.guide.util.PropertiesUtil;
import com.example.guide.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Service
public class SearchService {
    @Autowired(required = false)
    SearchMapper searchMapper;
    @Autowired
    HttpServletResponse response;
    @Autowired(required = false)
    RedisUtil redisUtil;

    private ThreadLocal<String> searchParam = new ThreadLocal<>();
    public ThreadLocal<String> path = new ThreadLocal<>();
    public ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

    public void setSearchParam(String param) {
        this.searchParam.set(param);
    }

    public String getSearchParam() {
        return searchParam.get();
    }

    public void search(String path) {
        File fileParent = new File(path);
        File[] fileChildren = fileParent.listFiles();
        if (fileChildren != null) {
            try {
                for (File file : fileChildren) {
                    if (".idea".equals(file.getName())) {
                        File file1 = new File(file.getParent());
                        String fileName = file1.getName();
                        if (matchName(fileName)) {
                            String filePath = searchMapper.getPathFromDatabase(fileName);
//                            System.out.println("filePath:" + filePath);
                            if (filePath == null) {
                                filePath = file1.getPath();
                                FileEntity fileEntity = new FileEntity();
                                fileEntity.setFileName(fileName);
                                fileEntity.setFilePath(filePath);
                                fileEntity.setTime(System.currentTimeMillis());
                                searchMapper.setPathToDatabase(fileEntity);
                            }
                            fileMap.put(fileName, filePath);
                        }
                    }
                    if (file.isDirectory()) {
                        search(file.getPath());
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchFromDatabase() {
        Map<String, String> map = new HashMap<>();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(getSearchParam());
        String searchKey = "%" + fileEntity.getFileName() + "%";
        List<FileEntity> fileEntityList = searchMapper.queryByName(searchKey);
        for (FileEntity fileEntity1 : fileEntityList) {
            map.put(fileEntity1.getFileName(), fileEntity1.getFilePath());
        }
        try {
            setResponse(map);
            System.out.println(map);
            System.out.println("========================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSearchBound() throws IOException {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        this.path.set(propertiesUtil.getProperties("searchBound"));
    }

    private Boolean matchName(String clearFileName) {
        if (clearFileName != null) {
            String patternString;
            if (searchParam != null) {
                patternString = ".*" + searchParam.get().toUpperCase() + ".*";
            } else {
                patternString = ".*";
            }

            if (Pattern.matches(patternString, clearFileName.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public void setResponse(Map map) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String jsonString = JSON.toJSONString(map);
        response.getWriter().write(jsonString);
//        System.out.println("jsonObject:" + jsonString);
    }

    public void clearDatabase() {
        searchMapper.deleteAll();
    }

//    public void test() {
//        redisUtil.set("1","1",1);
//    }

// =====================================================just for test=================================================================

    public void efficiencyTest() {
        FileEntity fileEntity = new FileEntity("111", "333", System.currentTimeMillis());
        searchMapper.setPathToDatabase(fileEntity);
        List<FileEntity> fileList = searchMapper.getAllRepeatData();
        Iterator iterator = fileList.iterator();
        while (iterator.hasNext()) {
            FileEntity fileData = (FileEntity) iterator.next();
            if (iterator.hasNext()) {
                searchMapper.deleteDataById(fileData.getId());
            }else {
                searchMapper.updatePathForDatabase("444",fileData.getFileName());
            }
        }
    }
}

