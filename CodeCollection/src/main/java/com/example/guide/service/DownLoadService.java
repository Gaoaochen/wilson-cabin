package com.example.guide.service;

import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class DownLoadService {

    public void downLoad(HttpServletResponse response,String fileName){
        File file =new File("/Users/lidongqi/Desktop/MyCode/"+fileName);
        try {
            InputStream inputStream=new FileInputStream(file);
            OutputStream outputStream=response.getOutputStream();
            byte[] buffers =new byte[1024];
            int len;
            while ((len=inputStream.read(buffers))!=-1){
               outputStream.write(buffers,0,len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}