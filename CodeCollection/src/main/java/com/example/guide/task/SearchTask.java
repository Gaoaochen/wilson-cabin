package com.example.guide.task;

import com.example.guide.dao.SearchMapper;
import com.example.guide.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Component
@ConditionalOnProperty(prefix = "enable",name = "scheduled",havingValue = "true")
public class SearchTask {

    @Autowired
    SearchMapper searchMapper;

    public ThreadLocal<String> path = new ThreadLocal<>();

    public void getSearchBound() throws IOException {
        Properties properties = new Properties();
        Resource resource = new ClassPathResource("application.yml");
        InputStream inputStream = resource.getInputStream();
        properties.load(inputStream);
        this.path.set(properties.getProperty("searchBound"));
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
                        String filePath = searchMapper.getPathFromDatabase(fileName);
//                        System.out.println("filePath------"+filePath);

                        if (filePath == null) {
                            filePath = file1.getPath();
                            FileEntity fileEntity = new FileEntity();
                            fileEntity.setFileName(fileName);
                            fileEntity.setFilePath(filePath);
                            fileEntity.setTime(System.currentTimeMillis());
                            searchMapper.setPathToDatabase(fileEntity);
//                            System.out.println("fileName:" + fileName + "   " + "filePath:" + filePath);
                            continue;
                        }
                        File unknownFile = new File(filePath);
                        if (!unknownFile.exists()) {
                            searchMapper.deletePathForDatabase(fileName);
                            continue;
                        }
//                        System.out.println("fileName:" + fileName + "   " + "filePath:" + filePath);
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
//    @Scheduled(fixedRate = 1000)
//    public void test(){
//        System.out.println("开关未生效");
//    }

    @Scheduled(fixedRate = 10*60*1000)
    public void executeSearch() throws IOException {
        try {
            getSearchBound();
            search(path.get());
            System.out.println("executeSearch:"+Thread.currentThread().getName());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(initialDelay = 10*1000,fixedRate = 10*60*1000)
    public void deleteAllRepeatData(){
        List<FileEntity> fileEntityList=searchMapper.getAllRepeatData();
        Iterator iterator =fileEntityList.iterator();
        while (iterator.hasNext()){
            FileEntity fileEntity=(FileEntity)iterator.next();
            if (iterator.hasNext()){
                searchMapper.deleteDataById(fileEntity.getId());
            }
        }
        System.out.println("deleteAllRepeatData:"+Thread.currentThread().getName());
    }
}
