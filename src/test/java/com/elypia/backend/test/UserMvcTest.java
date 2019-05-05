package com.elypia.backend.test;

import com.elypia.backend.controllers.UserController;
import com.elypia.backend.database.entities.User;
import com.elypia.backend.database.repositories.UserRepository;
import com.elypia.backend.database.repositories.VerificationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This this is mocking the database and simulating
 * HTTP requests in order to ensure we're getting the response statuses
 * and body we're expecting with the given scenarios as well as aid
 * in documentation generated.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserMvcTest {

    private static BCryptPasswordEncoder encoder;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepo;

    @MockBean
    private VerificationRepository verifyRepo;

    @BeforeAll
    public static void beforeAll() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    public void emailNotExist() throws Exception {
        mvc.perform(get("/api/users/exists").param("email", "nou@nou.nou"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void emailDoesExist() throws Exception {
        User user = new User(1, "seth@elypia.com", encoder.encode("@123pass@"), true, true);
        Mockito.when(userRepo.findByEmail("seth@elypia.com")).thenReturn(user);

        mvc.perform(get("/api/users/exists").param("email", "seth@elypia.com"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void teapotNotExist() throws Exception {
        mvc.perform(get("/api/users/exists").param("email", "seth@teapot.com"))
            .andExpect(status().isIAmATeapot())
            .andExpect(content().string("Just tip me over and pour me out!"));
    }
}
