package com.example.guide.entity;

public class FileEntity {

    private String fileName;
    private String filePath;
    private long time;
    private int id;

    public FileEntity(String fileName,String filePath,long time){
        this.fileName =fileName;
        this.filePath =filePath;
        this.time =time;
    }

    public FileEntity(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", time=" + time +
                ", id=" + id +
                '}';
    }
}
