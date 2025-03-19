//package com.example.demo.service.impl;
//
//import com.example.demo.entity.TestModel;
//import com.example.demo.mapper.TestMapper;
//import com.example.demo.service.inter.TestService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TestServiceImpl implements TestService {
//    private TestMapper testMapper;
//
//    @Override
//    public TestModel getTest(int id) {
//        return testMapper.getTest(id);
//    }
//
//    @Override
//    public void postTest(TestModel test) {
//        testMapper.postTest(test);
//    }
//
//    @Override
//    public void putTest(int id, TestModel test) {
//        test.setId(id);
//        testMapper.putTest(test);
//    }
//
//    @Override
//    public void deleteTest(int id) {
//        testMapper.deleteTest(id);
//    }
//}
