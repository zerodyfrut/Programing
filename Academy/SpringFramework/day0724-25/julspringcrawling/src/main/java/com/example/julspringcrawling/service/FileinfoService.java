package com.example.julspringcrawling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.julspringcrawling.dao.FileinfoDao;
import com.example.julspringcrawling.dto.FileInfo;

@Service
public class FileinfoService {
    @Autowired
    FileinfoDao dao;

    public int inserFile(FileInfo file){
        return dao.insertFile(file);
    }
    public List<FileInfo> list(){
        return dao.list();
    }
    public FileInfo fileOne(int fileid){
        return dao.fileOne(fileid);
    }
}
