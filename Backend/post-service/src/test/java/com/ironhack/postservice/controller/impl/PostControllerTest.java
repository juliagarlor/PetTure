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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PostControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PostRepository postRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        Let's create a post
        Post post = new Post("Holi caracoli", 1L, "Tenacitas");
        postRepository.save(post);
    }

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    void getPostsById_existingId_PostDTO() throws Exception {
        Post post = postRepository.findAll().get(0);
//        Let's look for the post
        MvcResult result = mockMvc.perform(get("/post/" + post.getId())).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertTrue(result.getResponse().getContentAsString().contains("Holi caracoli"));
    }

    @Test
    void getPostsById_nonExistingId_Exception() throws Exception {
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(get("/post/" + (post.getId()) + 1)).andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not post with such an ID"));
    }

    @Test
    void getPostsByPicId_existingPicId_ListPostDTO() throws Exception {
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(get("/posts/picture/" + post.getPictureId())).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertTrue(result.getResponse().getContentAsString().contains("Holi caracoli"));
    }

    @Test
    void getPostsByPicId_nonExistingPicId_EmptyList() throws Exception {
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(get("/posts/picture/" + (post.getPictureId() + 1))).andExpect(status().isOk())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertFalse(result.getResponse().getContentAsString().contains("Holi caracoli"));
    }

    @Test
    void getPostsByUsername_existingUsername_ListPostDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/posts/user/Tenacitas")).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertTrue(result.getResponse().getContentAsString().contains("Holi caracoli"));
    }

    @Test
    void getPostsByUsername_nonExistingUsername_Exception() throws Exception {
        MvcResult result = mockMvc.perform(get("/post/picture/SnowBall"))
                .andExpect(status().isNotFound())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertFalse(result.getResponse().getContentAsString().contains("Holi caracoli"));
    }

    @Test
    void getAllPosts_void_ListPostDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/posts")).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertTrue(result.getResponse().getContentAsString().contains("Holi caracoli"));
    }

    @Test
    void newPost_validPostDTO_PostDTO() throws Exception {
        PostDTO test = new PostDTO("Meow", 2L, "SnowBall");
        String body = objectMapper.writeValueAsString(test);
        MvcResult result = mockMvc.perform(post("/post").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("SnowBall"));
        assertTrue(result.getResponse().getContentAsString().contains("Meow"));
        assertFalse(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertFalse(result.getResponse().getContentAsString().contains("Holi caracoli"));
    }

    @Test
    void newPost_invalidPostDTO_Exception() throws Exception {
//        Without body
        PostDTO test = new PostDTO("", 2L, "SnowBall");
        String body = objectMapper.writeValueAsString(test);
        MvcResult result = mockMvc.perform(post("/post").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Tell me your username"));

//        Without picture
        PostDTO test2 = new PostDTO("Meow", null, "SnowBall");
        String body2 = objectMapper.writeValueAsString(test2);
        MvcResult result2 = mockMvc.perform(post("/post").content(body2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result2.getResolvedException().toString().contains("A post must be associated to an image, this is not twitter"));

//        Without username
        PostDTO test3 = new PostDTO("Meow", 2L, "");
        String body3 = objectMapper.writeValueAsString(test3);
        MvcResult result3 = mockMvc.perform(post("/post").content(body3).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result3.getResolvedException().toString().contains("A post must be associated to an user"));
    }

    @Test
    void updateLicks_existingPostId_PostDTO() throws Exception {
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(put("/licks/" + post.getId()).content("")).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertTrue(result.getResponse().getContentAsString().contains("\"licks\":1"));
    }

    @Test
    void updateLicks_nonExistingPostId_Exception() throws Exception {
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(put("/licks/" + (post.getId() + 1)).content(""))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not post with such an ID"));
    }

    @Test
    void removePost_existingId_PicId() throws Exception {
        assertEquals(1, postRepository.findAll().size());
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(delete("/post/" + post.getId()))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1"));
        assertEquals(0, postRepository.findAll().size());
    }

    @Test
    void removePost_nonExistingId_Exception() throws Exception {
        assertEquals(1, postRepository.findAll().size());
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(delete("/post/" + (post.getId() + 1)))
                .andExpect(status().isNotFound()).andReturn();
        assertEquals(1, postRepository.findAll().size());
    }

    @Test
    void removePostsByPic_existingId_void() throws Exception {
        assertEquals(1, postRepository.findAll().size());
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(delete("/posts/picture/" + post.getPictureId()))
                .andExpect(status().isNoContent()).andReturn();
        assertEquals(0, postRepository.findAll().size());
    }

    @Test
    void removePostsByPic_nonExistingId_EmptyList() throws Exception {
        assertEquals(1, postRepository.findAll().size());
        Post post = postRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(delete("/posts/picture/" + (post.getPictureId() + 1))).andExpect(status().isNoContent())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertFalse(result.getResponse().getContentAsString().contains("Holi caracoli"));
        assertEquals(1, postRepository.findAll().size());
    }
}