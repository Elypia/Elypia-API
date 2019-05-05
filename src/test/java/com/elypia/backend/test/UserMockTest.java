package com.elypia.backend.test;

import com.elypia.backend.controllers.UserController;
import com.elypia.backend.database.entities.User;
import com.elypia.backend.database.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Instead of using an in-memory database we just fake database
 * responses and use the fake responses to test our controller methods.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMockTest {

    private static BCryptPasswordEncoder encoder;

    @Autowired
    private UserController controller;

    @MockBean
    private UserRepository userRepo;

    @BeforeAll
    public static void beforeAll() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    public void getUserTest() {
        User user = new User(1, "seth@elypia.com", encoder.encode("@123pass@"), true, true);
        Mockito.when(userRepo.findById(1)).thenReturn(user);
        User resp = controller.getUser(1);

        assertAll("Match user correctly",
            () -> assertEquals(1, resp.getId()),
            () -> assertEquals("seth@elypia.com", resp.getEmail()),
            () -> assertNull(resp.getPhoneNumber()),
            () -> assertFalse(encoder.matches("AaAa123@@", resp.getPassword())),
            () -> assertTrue(resp.isVerified()),
            () -> assertTrue(resp.isAdmin())
        );
    }
}
