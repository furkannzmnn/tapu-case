package com.example.tapuurl.controller;

import com.example.tapuurl.IntegrationTestSupport;
import com.example.tapuurl.dto.UserDto;
import com.example.tapuurl.dto.request.UserSaveRequest;
import com.example.tapuurl.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class UserControllerTest extends IntegrationTestSupport {


    @Test
    void signUp() throws Exception {

        User user = userRepository.save(generateUser());
        userSaveRequestConverter.convertToSaveRequest(generateSaveRequest());

        UserSaveRequest create = generateSaveRequest();

        this.mockMvc.perform(post(USER_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(create)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(create)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user.password", is(user.getPassword())))
                .andExpect(jsonPath("$.user.userName", is(user.getUserName())));

    }

}