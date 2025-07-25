package com.example.julspringcrawling.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.julspringcrawling.command.BookInfo;

@Service
public class ExcelService {
    // RestTemplate주입
    @Autowired
    RestTemplate restTemplate;

    // naver 책 검색 api 요청
    public BookInfo bookExcel(String d_titl) {
        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                .path("/v1/search/book_adv.json")
                .queryParam("d_titl", d_titl)
                .queryParam("display", "10")
                .queryParam("start", "1")
                .queryParam("sort", "sim")
                .encode().build().toUri();
        // 한글검색 이기에 인코딩, 후에 URI로 뽑아냄

        // 헤더 정보 추가
        RequestEntity<Void> req = RequestEntity.get(uri)
                .header("X-Naver-Client-Id", "EwWbwkacpkvJmnglE0Vz")
                .header("X-Naver-Client-Secret", "gGQDfUhiMn")
                .build();

        // json 데이터 확인용
        //ResponseEntity<String> response1 = restTemplate.exchange(req, String.class);
        //System.out.println(response1.getBody());

        //JSON -> BookInfo
        ResponseEntity<BookInfo> response=restTemplate.exchange(req, BookInfo.class);
        BookInfo info=response.getBody();

        return info;

    }

}
