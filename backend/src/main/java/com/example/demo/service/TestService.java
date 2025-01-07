package com.example.demo.service;

import com.example.demo.entity.TestModel;
import com.example.demo.mapper.TestMapper;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private TestMapper testMapper;

    public TestModel getTest(int id) {
        return testMapper.getTest(id);
    }

    public void postTest(TestModel test) {
        testMapper.postTest(test);
    }

    public void putTest(int id, TestModel test) {
        test.setId(id);
        testMapper.putTest(test);
    }

    public void deleteTest(int id) {
        testMapper.deleteTest(id);
    }
}
