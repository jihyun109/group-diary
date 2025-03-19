//package com.example.demo.controller;
//
//import com.example.demo.entity.TestModel;
//import com.example.demo.service.inter.TestService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//
//@RestController // 화면 구성을 위한 템플릿을 사용하는 대신에 결과 데이터를 직렬화하고 HTTP응답 본문에 직접 써서 반환하는 REST 컨트롤러
//public class TestController {
//    private TestService testService;
//    public TestController(TestService testService) {
//        this.testService = testService;
//    }
//
//    @GetMapping("/tests/{id}")
//    public HashMap<String, Object> getTest(@PathVariable(required = true) int id) {
//        TestModel test = testService.getTest(id);
//
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("result", "success");
//        result.put("data", test);
//        return result;
//    }
//
//    @PostMapping("/tests")
//    public HashMap<String, String> postTest(@RequestBody TestModel test) {
//        testService.postTest(test);
//
//        HashMap<String, String> result = new HashMap<>();
//        result.put("result", "success");
//        return result;
//    }
//
//    @PutMapping("/tests/{id}")
//    public HashMap<String, String> putTest(@RequestBody TestModel test, @PathVariable(required = true) int id) {
//        testService.putTest(id, test);
//
//        HashMap<String, String> result = new HashMap<>();
//        result.put("result", "success");
//        return result;
//    }
//
//    @DeleteMapping("/tests/{id}")
//    public HashMap<String, String> deleteMember(@PathVariable(required = true) int id, @RequestParam(defaultValue = "succ") String succMsg) {
//        testService.deleteTest(id);
//
//        HashMap<String, String> result = new HashMap<>();
//        result.put("result", succMsg);
//        return result;
//    }
//}
