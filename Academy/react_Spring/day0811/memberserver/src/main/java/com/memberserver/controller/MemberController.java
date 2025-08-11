package com.memberserver.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memberserver.entity.Member;
import com.memberserver.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    MemberRepository memberRepository;
    // multipart/form-data 형식의 요청만 처리하도록 제한

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Member> saveMember(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam MultipartFile image,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String role) throws IOException {
        String uploadDir = new ClassPathResource("static/img").getFile().getAbsolutePath();
        // 파일의 경로를 문자열로 받아옴. static/img안의 파일을 가져와서 절대경로를 문자열로 반환함
        // resources/static/img 폴더의 실제 **물리 경로(절대경로)**를 가져옴

        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        // 뭔지모르지만 랜덤한 값에 파일의 원래이름을 더해서 반환
        // 파일 이름이 중복되지 않도록 UUID(랜덤 문자열) + 원본 파일명 조합으로 새 이름 생성
        Path filePath = Paths.get(uploadDir, fileName);
        // 뭔지모르지만 파일경로
        // -> 저장할 전체 경로 생성
        Files.write(filePath, image.getBytes());
        // 뭔지모르지만 그냥 모르겠음.
        // ->업로드된 파일 내용을 byte 배열로 꺼내서 지정한 경로에 씀 → 이미지 저장 완료

        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setImage("/img/" + fileName);
        member.setAddress(address);
        member.setRole(role);
        member.setPhone(phone);

        Member saved = memberRepository.save(member);
        // memeber에다가 세팅해서 saved에 넣음?
        // memberRepository(JPA)를 통해 DB에 저장
        // 저장 후에는 DB에서 자동 생성된 PK 값까지 채워진 Member 객체 반환됨

        return ResponseEntity.ok(saved);
        // 저장된 Member 객체를 JSON 형태로 응답

    }

    @GetMapping
    public List<Member> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members;

    
        // Member m = new Member();
        // m.setInNo(1L);
        // m.setId("testid");
        // m.setName("테스트");
        // m.setPhone("010-0000-0000");
        // m.setAddress("서울시");
        // m.setRole("USER");
        // m.setImage(null);

        // return List.of(m);
    }

}
