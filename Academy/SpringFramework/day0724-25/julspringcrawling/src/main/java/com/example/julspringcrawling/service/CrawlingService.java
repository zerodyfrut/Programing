package com.example.julspringcrawling.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.example.julspringcrawling.command.BibleCommand;
import com.example.julspringcrawling.command.NewsCommand;

import kr.solde.DownloadBroker;

@Service
public class CrawlingService {

    @Autowired
    private ResourceLoader resourceLoader;
    // 어플리케이션 자원에 접근

    public BibleCommand crawling2(String b) {
        BibleCommand command = new BibleCommand();
        String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BosyMatter?qt_ty=QT1";
        url += "&Base_de=" + b + "&bibleType=1";

        try {
            Document doc = Jsoup.connect(url).post();
            Element bible_text = doc.selectFirst(".bible_text");
            command.setBible_text(bible_text.text());

            Element bibleinfo_box = doc.select(".bibleinfo_box").first();
            command.setBibleinfo_box(bibleinfo_box.text());

            Elements liList = doc.select(".body_list > li");
            for (Element li : liList) {
                String n = li.select(".num").first().text();
                String text = li.select(".info").first().text();
                command.getLiList().add(n + ":" + text);
            } // for

            Element itag = doc.select(".img > img").first();
            // 첫번째 그림
            String dPath = "https://sum.su.or.kr:8888" + itag.attr("src").trim();
            String fileName = dPath.substring(dPath.lastIndexOf("/") + 1);
            // 확장자 포함 원본 파일명
            // static폴더에 img 폴더 생성할 것!!
            Resource resource = resourceLoader.getResource("classpath:/static/img/");
            // classpath: java or resources 폴더
            File file = resource.getFile(); // File 객체 - 폴더나 파일 표현
            // file.getAbsolutePath() -> img 폴더의 경로
            Runnable r = new DownloadBroker(dPath, file.getAbsolutePath() + "/" + fileName);
            Thread dload = new Thread(r);

            dload.start();
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i + 1);
            }

            System.out.println();
            System.out.println("================");

            command.setFilename(fileName); // view에 파일명 전달

        } catch (IOException e) {
            e.printStackTrace();
        }

        return command;
    }

    public List<NewsCommand> crawling() {
        List<NewsCommand> list = new ArrayList<>();
        String url = "https://news.naver.com/section/105";
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(doc.body());
        Elements element = doc.select("ul.sa_list div.sa_text");

        for (Element div : element) {
            NewsCommand news = new NewsCommand(div.select("strong").text(), div.select("a").attr("href"));
            list.add(news);
        }

        return list;
    }

}
