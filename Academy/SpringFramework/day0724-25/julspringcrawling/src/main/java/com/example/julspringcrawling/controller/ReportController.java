package com.example.julspringcrawling.controller;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.julspringcrawling.command.Report;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ReportController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/report/form")
    public void form() {
    }

    @PostMapping("/report/submit1")
    public String submit1(@RequestParam("stuno") String stuno,
            @RequestParam("report") MultipartFile report, Model m) {
        // 업로드 파일 이동
        // 1. 파일명 중복 안되기 생성(덮어쓰기 처리됨)
        // 여기선 makeName 메소드를 통해 중복을 최대한 방지함.
        String filename = makeName(report.getOriginalFilename());
        // System.out.println("파일의 이름" + filename);

        // 2. 원하는 위치로 이동
        // (파일이 이미지고, 클라이언트 화면에 보이고 싶다면 프로젝트 static 폴더로 이동)
        // upload 메소드를 통해 파열 경로에 접근, 파일을 생성함? 그 뒤에 경로를 리턴
        String uploadPath = upload(report, filename);
        // System.out.println("파일의 위치" + uploadPath);

        // m.addAttribute("filename", filename);
        m.addAttribute("path", uploadPath);

        return "/report/complete";
    }

    @PostMapping("/report/submit2")
    public String submit2(MultipartHttpServletRequest request, Model m) {
        // 일반 파라미터는 자료형 변수에 getParameter 메소드
        String stuNo = request.getParameter("stuno");
        // 파일 파라미터는 MultipartFile 변수에 getFile 메소드
        MultipartFile report = request.getFile("report");

        // 1. 파일명 중복 안되기 생성(덮어쓰기 처리됨)
        // 여기선 makeName 메소드를 통해 중복을 최대한 방지함.
        String filename = makeName(report.getOriginalFilename());

        // 2. 원하는 위치로 이동
        // (파일이 이미지고, 클라이언트 화면에 보이고 싶다면 프로젝트 static 폴더로 이동)
        // upload 메소드를 통해 파열 경로에 접근, 파일을 생성함? 그 뒤에 경로를 리턴
        String uploadPath = upload(report, filename);

        m.addAttribute("stuNo", stuNo);
        m.addAttribute("path", uploadPath);

        return "report/complete";
    }

    @PostMapping("/report/submit3")
    public String postMethodName(Report command, Model m) {
        // 업로드 파일 이동
        // 1. 파일명 중복 안되기 생성(덮어쓰기 처리됨)
        // 여기선 makeName 메소드를 통해 중복을 최대한 방지함.
        String filename = makeName(command.getReport().getOriginalFilename());

        // 2. 원하는 위치로 이동
        // (파일이 이미지고, 클라이언트 화면에 보이고 싶다면 프로젝트 static 폴더로 이동)
        // upload 메소드를 통해 파열 경로에 접근, 파일을 생성함? 그 뒤에 경로를 리턴
        String uploadPath = upload(command.getReport(), filename);

        m.addAttribute("stuNo", command.getStuno());
        m.addAttribute("path", uploadPath);

        System.out.println("학번: " + command.getStuno());
        System.out.println("파일: " + command.getReport()); // null일 수도
        System.out.println("파일이름: " + command.getReport().getOriginalFilename()); // NPE 날 수도

        return "report/complete";

    }

    private String makeName(String oriName) {
        long currentTime = System.currentTimeMillis(); // 현재시간
        Random random = new Random();
        int r = random.nextInt(50); // 0~49 랜덤
        // int r=random.nextInt(1,51); // 1~50 랜덤

        int index = oriName.lastIndexOf("."); // 마지막 .위치
        String ext = oriName.substring(index + 1);// 마지막 .위치 뒤로 자름

        return currentTime + "_" + r + "." + ext; // 현재시간_랜덤번호.확장자
    }

    private String upload(MultipartFile report, String filename) {
        // static/upload 폴더 파일 이동 후 파일 경로를 리턴
        String path = "";
        try {
            Resource resource = resourceLoader.getResource("classpath:/static/upload/");
            String uploadPath = resource.getFile().getAbsolutePath();

            report.transferTo(new File(uploadPath, filename));

            path = "/upload/" + filename;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
