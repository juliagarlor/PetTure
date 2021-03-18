package com.ironhack.userservice.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.userservice.model.*;
import com.ironhack.userservice.repository.*;
import com.ironhack.userservice.utils.dtos.*;
import com.ironhack.userservice.utils.enums.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        User user = new User("Tenacitas", "12345", 1L, Visibility.PUBLIC);
        User buddy = new User("ayudanteDeSantaClaus", "6789", 2L, Visibility.PUBLIC);
        User request = new User("Puchi", "10112", 3L, Visibility.PUBLIC);
        userRepository.saveAll(List.of(buddy, request));
        List<User> buddies = user.getBuddies();
        buddies.add(buddy);
        user.setBuddies(buddies);
        List<User> requests = user.getRequests();
        requests.add(request);
        user.setRequests(requests);
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getUserByUserName_existingUsername_UserDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/search/Tenacitas")).andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertTrue(result.getResponse().getContentAsString().contains("PUBLIC"));
    }

    @Test
    void getUserByUserName_nonExistingUsername_Exception() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/search/SnowBall")).andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void getProfileByUserName_existingUsername_ProfileDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/basic-profile/Tenacitas")).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1"));
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
    }

    @Test
    void getProfileByUserName_nonExistingUsername_Exception() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/basic-profile/SnowBall")).andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void getBuddies_existingUsername_ListProfileDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/buddies/Tenacitas")).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("ayudanteDeSantaClaus"));
        assertTrue(result.getResponse().getContentAsString().contains("2"));
        assertFalse(result.getResponse().getContentAsString().contains("PUBLIC"));
    }

    @Test
    void getBuddies_nonExistingUsername_Exception() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/buddies/SnowBall")).andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void getRequests_existingUsername_ListProfileDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/requests/Tenacitas")).andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Puchi"));
        assertTrue(result.getResponse().getContentAsString().contains("3"));
        assertFalse(result.getResponse().getContentAsString().contains("PUBLIC"));
    }

    @Test
    void getRequests_nonExistingUsername_ListProfileDTO() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/requests/SnowBall")).andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void getPublicProfiles_void_ListOfUsernames() throws Exception {
        MvcResult result = mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
        assertTrue(result.getResponse().getContentAsString().contains("ayudanteDeSantaClaus"));
        assertTrue(result.getResponse().getContentAsString().contains("Puchi"));
    }

    @Test
    void registerUser_validUserDTO_UserDTO() throws Exception {
        UserDTO test = new UserDTO("SnowBall", "1234", 4L, "PRIVATE",
                new ArrayList<>(), new ArrayList<>(), "USER");
        String body = objectMapper.writeValueAsString(test);
        MvcResult result = mockMvc.perform(post("/user/auth/register").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1234"));
        assertTrue(result.getResponse().getContentAsString().contains("SnowBall"));
    }

    @Test
    void registerUser_invalidUserDTO_Exception() throws Exception {
//        Without username
        UserDTO test = new UserDTO("", "1234", 4L, "PRIVATE",
                new ArrayList<>(), new ArrayList<>(), "USER");
        String body = objectMapper.writeValueAsString(test);
        MvcResult result = mockMvc.perform(post("/user/auth/register").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Be creative, it's your username"));

//        Without password
        UserDTO test2 = new UserDTO("SnowBall", "", 4L, "PRIVATE",
                new ArrayList<>(), new ArrayList<>(), "USER");
        String body2 = objectMapper.writeValueAsString(test2);
        MvcResult result2 = mockMvc.perform(post("/user/auth/register").content(body2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result2.getResolvedException().toString().contains("Password is compulsory"));

//        Without picture
        UserDTO test3 = new UserDTO("SnowBall", "1234", null, "PRIVATE",
                new ArrayList<>(), new ArrayList<>(), "USER");
        String body3 = objectMapper.writeValueAsString(test3);
        MvcResult result3 = mockMvc.perform(post("/user/auth/register").content(body3).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result3.getResolvedException().toString().contains("Pick your favourite pic"));

//        Without visibility
        UserDTO test4 = new UserDTO("SnowBall", "1234", 4L, "",
                new ArrayList<>(), new ArrayList<>(), "USER");
        String body4 = objectMapper.writeValueAsString(test4);
        MvcResult result4 = mockMvc.perform(post("/user/auth/register").content(body4).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(result4.getResolvedException().toString().contains("Please, enter a visibility value: public or private"));
    }

    @Test
    void updateProfilePic_existingUsernameValidProfilePic_ProfileDTO() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/profile-pic/Tenacitas").content("5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("5"));
        assertFalse(result.getResponse().getContentAsString().contains("1"));
    }

    @Test
    void updateProfilePic_nonExistingUsernameValidProfilePic_ProfileDTO() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/profile-pic/SnowBall").content("5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void updateProfilePic_existingUsernameInvalidProfilePic_ProfileDTO() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/profile-pic/Tenacitas").content("a").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResolvedException().toString());
        assertTrue(result.getResolvedException().toString().contains("The profile picture id must be a number"));
    }

    @Test
    void addABuddy_existingUserExistingBuddy_UserDTO() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/buddy/Tenacitas").content("Puchi").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
//        Let's go for Tenacitas
        User tenacitas = userRepository.findById("Tenacitas").get();
        assertTrue(tenacitas.getBuddies().get(1).getUserName().equals("Puchi"));
        assertEquals(0, tenacitas.getRequests().size());
    }

    @Test
    void addABuddy_nonExistingUserExistingBuddy_Exception() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/buddy/SnowBall").content("Puchi").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void addABuddy_existingUserNonExistingBuddy_Exception() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/buddy/Tenacitas").content("SnowBall").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void addRequest_existingUserExistingRequest_UserDTO() throws Exception {
        User pisoni = new User("Pisoni", "5678", 6L, Visibility.PUBLIC);
        userRepository.save(pisoni);

        mockMvc.perform(put("/user/add/request/Tenacitas").content("Pisoni").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
//        Let's go for Tenacitas
        User tenacitas = userRepository.findById("Tenacitas").get();
        assertTrue(tenacitas.getRequests().get(1).getUserName().equals("Pisoni"));
        assertEquals(2, tenacitas.getRequests().size());
    }

    @Test
    void addRequest_nonExistingUserExistingRequest_Exception() throws Exception {
        User pisoni = new User("Pisoni", "5678", 6L, Visibility.PUBLIC);
        userRepository.save(pisoni);

        MvcResult result = mockMvc.perform(put("/user/add/request/SnowBall").content("Pisoni").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void addRequest_existingUserNonExistingRequest_Exception() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/add/request/Tenacitas").content("Pisoni").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void removeRequest_existingUserExistingRequest_UserDTO() throws Exception {
        mockMvc.perform(put("/user/remove/request/Tenacitas").content("Puchi").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
//        Let's go for Tenacitas
        User tenacitas = userRepository.findById("Tenacitas").get();
        assertEquals(0, tenacitas.getRequests().size());
    }

    @Test
    void removeRequest_nonExistingUserExistingRequest_UserDTO() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/remove/request/SnowBall").content("Puchi").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void removeRequest_existingUserNonExistingRequest_UserDTO() throws Exception {
        MvcResult result = mockMvc.perform(put("/user/remove/request/Tenacitas").content("SnowBall").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }

    @Test
    void removeUser_existingUsername_Username() throws Exception {
        MvcResult result = mockMvc.perform(delete("/user/Tenacitas"))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Tenacitas"));
    }

    @Test
    void removeUser_nonExistingUsername_Username() throws Exception {
        MvcResult result = mockMvc.perform(delete("/user/SnowBall"))
                .andExpect(status().isNotFound()).andReturn();
        assertTrue(result.getResolvedException().toString().contains("Not an user with such an username"));
    }
}