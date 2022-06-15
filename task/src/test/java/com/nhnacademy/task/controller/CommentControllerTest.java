package com.nhnacademy.task.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.service.CommentService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @SpyBean
    CommentService commentService;

    @MockBean
    CommentRepository commentRepository;

    @Test
    void registerComment() {
    }

    @Test
    void getAllComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }
}