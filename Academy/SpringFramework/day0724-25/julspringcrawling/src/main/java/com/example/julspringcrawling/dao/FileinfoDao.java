package com.example.julspringcrawling.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.julspringcrawling.dto.FileInfo;

@Mapper
public interface FileinfoDao {

    @Insert("insert into fileinfo (name,path,filesize,description)"
        +"values(#{name},#{path},#{filesize},#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insertFile(FileInfo file);

    @Select("select * from fileInfo")
    List <FileInfo> list();

    @Select("select *from fileinfo where fileid=#{fileid}")
    FileInfo fileOne(int fileid);
    
} 
