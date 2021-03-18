package com.ironhack.postservice.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.postservice.model.*;
import com.ironhack.postservice.repository.*;
import com.ironhack.postservice.utils.dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CommentaryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentaryRepository commentaryRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Post post = new Post("Holi caracoli", 1L, "Tenacitas");
        postRepository.save(post);
        Commentary comment = new Commentary("Snowball", "patata", post);
        commentaryRepository.save(comment);
    }

    @AfterEach
    void tearDown() {
        commentaryRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    void getCommentariesInPost_existingPostId_ListCommentaryDTO() throws Exception {
        Post test = postRepository.findAll().get(0);

        MvcResult result = mockMvc.perform(get("/commentaries/" + test.getId())).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Snowball"));
    }

    @Test
    void getCommentariesInPost_nonExistingPostId_EmptyList() throws Exception {
        Post test = postRepository.findAll().get(0);

        MvcResult result = mockMvc.perform(get("/commentaries/" + (test.getId() + 1)))
                .andExpect(status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().contains("Snowball"));
    }

    @Test
    void addCommentary_validCommentDTO_CommentDTO() throws Exception {
        assertEquals(1, commentaryRepository.findAll().size());

        Post post = postRepository.findAll().get(0);
        CommentaryDTO test = new CommentaryDTO("ayudanteDeSantaClaus", "queso", post.getId());
        String body = objectMapper.writeValueAsString(test);
        MvcResult result = mockMvc.perform(post("/commentary").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("ayudanteDeSantaClaus"));

        assertEquals(2, commentaryRepository.findAll().size());
    }

    @Test
    void addCommentary_invalidCommentDTO_CommentDTO() throws Exception {
        assertEquals(1, commentaryRepository.findAll().size());

        Post post = postRepository.findAll().get(0);
//        Without username
        CommentaryDTO test = new CommentaryDTO("", "queso", post.getId());
        String body = objectMapper.writeValueAsString(test);
        MvcResult result = mockMvc.perform(post("/commentary").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("[Be brave]"));

//        Without body
        CommentaryDTO test2 = new CommentaryDTO("ayudanteDeSantaClaus", "", post.getId());
        String body2 = objectMapper.writeValueAsString(test2);
        MvcResult result2 = mockMvc.perform(post("/commentary").content(body2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result2.getResolvedException().toString().contains("Say something to your friend"));

//        Without post
        CommentaryDTO test3 = new CommentaryDTO("ayudanteDeSantaClaus", "queso", null);
        String body3 = objectMapper.writeValueAsString(test3);
        MvcResult result3 = mockMvc.perform(post("/commentary").content(body3).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result3.getResolvedException().toString().contains("A comment shouldn't be alone"));

        assertEquals(1, commentaryRepository.findAll().size());
    }
}