package com.example.tapuurl;
import com.example.tapuurl.dto.converter.UserDtoConverter;
import com.example.tapuurl.dto.converter.UserSaveRequestConverter;
import com.example.tapuurl.repository.UserRepository;
import com.example.tapuurl.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class IntegrationTestSupport extends TestSupport{

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserDtoConverter userDtoConverter;

    @Autowired
    public UserSaveRequestConverter userSaveRequestConverter;

    @Autowired
    public UserService userService;

    @Autowired
    public ObjectMapper mapper;

}
