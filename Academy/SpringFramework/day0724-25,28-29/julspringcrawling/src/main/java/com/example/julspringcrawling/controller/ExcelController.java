package com.example.julspringcrawling.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.julspringcrawling.command.BookInfo;
import com.example.julspringcrawling.command.Item;
import com.example.julspringcrawling.service.ExcelService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExcelController {

    @Autowired
    ExcelService service;

    @GetMapping("/title")
    public void form() {
    }

    @GetMapping("/bookExcel")
    public void bookExcel(@RequestParam("d_titl") String d_titl, HttpServletResponse response) {
        // 서비스에서 api요청
        BookInfo info = service.bookExcel(d_titl);
        // 결과물 -> Excel 객체
        Workbook workbook = new SXSSFWorkbook();// excel문서

        Sheet sheet = workbook.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // header
        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("title");
        row.createCell(1).setCellValue("link");
        row.createCell(2).setCellValue("author");
        row.createCell(3).setCellValue("publisher");
        row.createCell(4).setCellValue("pubdate");
        row.createCell(5).setCellValue("isbn");
        row.createCell(6).setCellValue("image");
        // 아마 첫행의 값 주제? 그런 느낌

        // body
        for (int i = 0; i < info.getItems().size(); i++) {
            row = sheet.createRow(rowNum++);
            Item m = info.getItems().get(i);
            row.createCell(0).setCellValue(m.getTitle());
            row.createCell(1).setCellValue(m.getLink());
            row.createCell(2).setCellValue(m.getAuthor());
            row.createCell(3).setCellValue(m.getPublisher());
            row.createCell(4).setCellValue(format(m.getPubdate()));
            row.createCell(5).setCellValue(m.getIsbn());

            // row.createCell(6)-> 이미지
            // 네이버에서 이미지의 주소를 받아다 해당 cell에 저장
            try {
                URL url = new URL(m.getImage());
                InputStream is = url.openStream();
                byte[] bytes = IOUtils.toByteArray(is);// 이미지를 byte 배열로 가져옴

                int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
                is.close();
                CreationHelper helper = workbook.getCreationHelper();
                Drawing<?> drawing = sheet.createDrawingPatriarch(); // 그려주는 애?
                ClientAnchor anchor = helper.createClientAnchor();
                anchor.setRow1(rowNum - 1);
                anchor.setCol1(6);
                anchor.setRow2(rowNum);
                anchor.setCol2(7);

                drawing.createPicture(anchor, pictureIdx);

                cell = row.createCell(6);
                int w = 20 * 256;
                sheet.setColumnWidth(1, w);

                short h = 120 * 20;
                cell.getRow().setHeight(h);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // Excel 다운로드

        String filename = "";
        try {
            filename = new String((d_titl + "_검색.xlsx").getBytes("utf-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        response.setContentType("ms-vnd/excel;");

        response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\";");

        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String format(String pubdate){
        try{
            SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMdd");
            Date pub=sdf1.parse(pubdate);
            // Date->String
            SimpleDateFormat sdf2=new SimpleDateFormat("yyyy.MM.dd");
            return sdf2.format(pub);
        }catch(ParseException e){
            e.printStackTrace();
        }

        return "";
    }

}
