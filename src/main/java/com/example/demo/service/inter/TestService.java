package com.example.demo.service.inter;

import com.example.demo.entity.TestEntity;

public interface TestService {
    TestEntity getTest(int id);

    void postTest(TestEntity test);

    void putTest(int id, TestEntity test);

    void deleteTest(int id);
}
