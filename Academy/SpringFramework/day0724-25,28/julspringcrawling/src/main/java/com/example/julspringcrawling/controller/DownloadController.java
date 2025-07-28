package com.example.julspringcrawling.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DownloadController {

    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping("/download")
    public void fileDownload(HttpServletResponse response) throws IOException {
        // 브라우저는 보여줄수 없는 파일이면 다운로드

        Resource resource=resourceLoader.getResource("classpath:/static/upload/");
        String uploadPath = resource.getFile().getAbsolutePath();

        File file = new File(uploadPath,"1753413737781_28.gif");
        // File의 생성자 변수는 서버상의 파일 경로.
        
        String filename = new String(file.getName().getBytes("utf-8"), "iso-8859-1");
        // 한글이름일 수 있으니 인코딩 처리
        
        response.setContentType("application/octet-stream;charset-utf-8");
        // 어떤 타입인지 불명확할 때, 다양한 타입을 다룰 수 있는 octet-stream

        response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\";");
        // 다운로드 받을 파일명 지정, attachment: 브라우저가 보여줄 수 있어도 다운로드
        response.setHeader("Content-Transfer-Encoding", "binary");

        // 지금까지 다운로드를 받기위한 설정이였음

        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        out.flush();
    }// 메서드가 끝나면 response가 브라우저로 전달됨.
}