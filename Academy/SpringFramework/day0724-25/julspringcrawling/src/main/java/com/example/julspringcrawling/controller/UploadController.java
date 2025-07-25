package com.example.julspringcrawling.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import com.example.julspringcrawling.dto.FileInfo;
import com.example.julspringcrawling.service.FileinfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UploadController {
    @Autowired
    FileinfoService service;

    @GetMapping("/filedownload/{fileid}")
    public void fileDownload(@PathVariable("fileid") int id,HttpServletResponse response,HttpServletRequest request)throws IOException {
        //DB Select - id에 맞는 레코드 한줄 꺼내옴
        FileInfo dto = service.fileOne(id);
        String path=ResourceUtils.getFile("classpath:static").toPath().toString();
        // 다운 받을 파일 선택
        File file=new File(path,dto.getPath());

        // 다운 받을 파일이름을 인코딩
        String fileName=new String (dto.getName().getBytes("utf-8"),"iso-8859-1");
        //어떤 응답종류인지 브라우저가 알아야 함
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
        // attachment : 보여줄 수 있는 형식의 파일도 무조건 다운로드
        // filename : 다운로드 받을 파일명 지정
        response.setHeader("Content-Transfer-Encoding", "binary");

        OutputStream out=response.getOutputStream();

        try(FileInputStream fis=new FileInputStream(file);){
            FileCopyUtils.copy(fis, out);
        }catch(Exception e){
            System.out.println("파일 없음");
        }
        out.flush();
    }

    @GetMapping("/list")
    public String list(Model m) {
        // DB 연동
        List<FileInfo>flist=service.list();
        m.addAttribute("flist", flist);
        return "file/list";
    }
    
    @GetMapping("/upload")
    public String form() {
        return "file/fileform";
    }

    @PostMapping("/upload")
    public String submit(@ModelAttribute("dto") FileInfo dto, @RequestParam("file") MultipartFile file) {
        
        if(!file.getOriginalFilename().equals("")){
            //upload
            String filename=upload(file);// 업로드 된 파일명
            //DB insert
            dto.setName(file.getOriginalFilename());
            dto.setPath("/upload/"+filename);
            dto.setFilesize(file.getSize());

            service.inserFile(dto);

            //model 파일 정보 추가
        }

        return "file/result";
    }

    private String makeFileName(String origName){
        //ms+random.확장자
        long currentTime=System.currentTimeMillis();
        Random random=new Random();
        int r=random.nextInt(50);//0~49
        int index =origName.lastIndexOf(".");
        String ext=origName.substring(index);

        return currentTime+"_"+r+ext;
    }

    private String upload(MultipartFile file) {
        String newFileName=makeFileName(file.getOriginalFilename());
        File newFile=null;
        try {
            String path =ResourceUtils.getFile("classpath:static/upload/").toPath().toString();
            newFile=new File(path,newFileName);
            file.transferTo(newFile);
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
        return newFileName;

    }
    
}
