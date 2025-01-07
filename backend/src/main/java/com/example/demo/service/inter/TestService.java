package com.example.demo.service.inter;

import com.example.demo.entity.TestModel;

public interface TestService {
    TestModel getTest(int id);

    void postTest(TestModel test);

    void putTest(int id, TestModel test);

    void deleteTest(int id);
}
