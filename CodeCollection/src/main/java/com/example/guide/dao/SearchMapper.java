package com.example.guide.dao;

import com.example.guide.entity.FileEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;

@Mapper
public interface SearchMapper {

    @Results(id = "FileTest", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "fileName", property = "fileName"),
            @Result(column = "filePath", property = "filePath"),
            @Result(column = "time", property = "time"),
    })

    //实际开发中应该尽量避免使用"select * "这种写法
    @Select("select * from file_guide")
    List<FileEntity> getAllFile();

    @Select("select distinct filePath from file_guide where fileName = #{fileName}")
    String getPathFromDatabase(String fileName);

    @Insert("insert into file_guide(id,fileName,filePath,time) value(#{id},#{fileName},#{filePath},#{time})")
    int setPathToDatabase(FileEntity fileEntity);

    @Update("update file_guide set filePath =#{filePath} where fileName =#{fileName}")
    int updatePathForDatabase(String filePath,String fileName);

    @Delete("delete from file_guide where fileName =#{fileName}")
    int deletePathForDatabase(String fileName);

    @Delete("delete from file_guide")
    int deleteAll();

    //查询全部重复数据
    @Select("select id from file_guide where filePath in(select filePath from file_guide group by filePath having count(1)>1)")
    List<FileEntity> getAllRepeatData();

    //正则匹配查询
    @Select("select fileName,filePath from file_guide where fileName like #{fileName}")
    List<FileEntity> queryByName(String fileName);

    @Delete("delete from file_guide where id =#{id}")
    int deleteDataById(int id);
}
