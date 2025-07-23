package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParamController {

    // ?param=값
    @GetMapping("/param1")
    @ResponseBody
    public String param1(@RequestParam(required = false) String param) {
        // @RequestParam("파라미터 이름") 매개변수와 같은 이름으로 전달된 값 저장
        return "param : " + param;
    }

    @GetMapping("/param2")
    @ResponseBody
    public String param2(@RequestParam("p") String param) {
        // @RequestParam("파라미터 이름") 지정된 이름으로 전달된 값 저장
        return "param : " + param;
    }

    @GetMapping("/param3")
    @ResponseBody
    public String param3(@RequestParam String param, 
                        @RequestParam(value = "i", required = false) int index) {
        // @RequestParam("파라미터 이름") 지정된 이름으로 전달된 값 저장
        // 기본타입인 경우 변환해서 저장
        return "param : " + param+"\n i : "+index;
    }

    @GetMapping("/param4")
    @ResponseBody
    public String param4(@RequestParam(value = "i",defaultValue = "0") int index) {
        // @RequestParam("defaultValue") 전달받은 값이 있다면 해당값을 저장, 없으면 defaultValue 저장
        return "i : " + index;
    }
}
