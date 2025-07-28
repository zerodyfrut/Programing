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
    // useGeneratedKeys 프라이머리 키를 가져와 fileid에 넘겨줌
    // insert후 자동 증가된 key값 저장
    int insertFile(FileInfo file);

    @Select("select * from fileInfo")
    List <FileInfo> list();

    @Select("select *from fileinfo where fileid=#{fileid}")
    FileInfo fileOne(int fileid);
    
} 
