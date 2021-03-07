package com.gsshop.service;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {
    /*
        JUNIT5 -> 리플렉션을 사용하면 프리베잇트 등 어떤 것도 들어갈 수 있습니다.
     */
    @Test
    @Disabled
    void create(){
        Study study = new Study();
        assertNotNull(study);
    }

    @Test
    void create1(){
        Study study = new Study();
        assertNotNull(study);
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("BeforeAll");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("AfterAll");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("BeforeEach");
    }

    @AfterEach
    void afterEach(){
        System.out.println("AfterEach");
    }
}