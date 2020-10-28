package com.example.guide.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public String getProperties(String key) {
        Properties properties = new Properties();
        Resource resource = new ClassPathResource("application.yml");
        InputStream inputStream ;
        try {
            inputStream = resource.getInputStream();
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
