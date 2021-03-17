package com.ironhack.pictureservice.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.pictureservice.model.*;
import com.ironhack.pictureservice.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.mock.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class PictureControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PictureRepository pictureRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        Creating and saving a new picture
        Picture picture = new Picture("Tenacitas", "jpeg", new byte[0]);
        pictureRepository.save(picture);
    }

    @AfterEach
    void tearDown() {
        pictureRepository.deleteAll();
    }

    @Test
    void getPicById_existingId_PictureDTO() throws Exception {
//        Let's look for the only pic in the database
        Picture pic = pictureRepository.findAll().get(0);
        MvcResult result = mockMvc.perform(get("/pic/" + pic.getId()))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
    }

    @Test
    void getPicById_nonExistingId_Exception() throws Exception {
//        Let's look for another picture
        Picture pic = pictureRepository.findAll().get(0);

        MvcResult result = mockMvc.perform(get("/pic/" + (pic.getId() + 1))).andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not a picture with such an ID"));
//        And another one
        MvcResult result2 = mockMvc.perform(get("/pic/" + (pic.getId() - 1))).andExpect(status().isNotFound()).andReturn();
        assertTrue(result2.getResolvedException().toString().contains("Not a picture with such an ID"));
    }

    @Test
    void newPic_validMultipartFile_PictureDTO() throws Exception {
        assertEquals(1, pictureRepository.findAll().size());
        MockMultipartFile file = new MockMultipartFile("myFile", "snowBall.jpeg", "jpeg",
                "snowBall.jpeg".getBytes());
        MvcResult result = mockMvc.perform(multipart("/pic").file(file))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("snowBall"));
        assertEquals(2, pictureRepository.findAll().size());
    }

    @Test
    void newPic_invalidMultipartFile_PictureDTO() throws Exception {
        assertEquals(1, pictureRepository.findAll().size());
//        Empty name
        MockMultipartFile file = new MockMultipartFile("myFile", "", "jpeg",
                "snowBall.jpeg".getBytes());
        MvcResult result = mockMvc.perform(multipart("/pic").file(file))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("A picture must have a name"));

//        Empty type
        MockMultipartFile file2 = new MockMultipartFile("myFile", "snowBall.jpeg", "",
                "snowBall.jpeg".getBytes());
        MvcResult result2 = mockMvc.perform(multipart("/pic").file(file2))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result2.getResolvedException().toString().contains("A picture must have a type"));
        assertEquals(1, pictureRepository.findAll().size());
    }

    @Test
    void removePic_existingId_void() throws Exception {
        assertEquals(1, pictureRepository.findAll().size());
//        Let's remove picture the picture
        Picture pic = pictureRepository.findAll().get(0);
        mockMvc.perform(delete("/pic/" + pic.getId())).andExpect(status().isNoContent()).andReturn();
        assertEquals(0, pictureRepository.findAll().size());
    }

    @Test
    void removePic_nonExistingId_void() throws Exception {
        assertEquals(1, pictureRepository.findAll().size());
        Picture pic = pictureRepository.findAll().get(0);
//        Let's remove a picture after the one we have
        MvcResult result = mockMvc.perform(delete("/pic/" + (pic.getId() + 1))).andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not a picture with such an ID"));
//        or the previous one
        MvcResult result2 = mockMvc.perform(delete("/pic/" + (pic.getId() - 1))).andExpect(status().isNotFound()).andReturn();
        assertTrue(result2.getResolvedException().toString().contains("Not a picture with such an ID"));
        assertEquals(1, pictureRepository.findAll().size());
    }
}