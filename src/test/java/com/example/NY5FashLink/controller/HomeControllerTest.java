package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HomeControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home_ShouldAddLoggedInUserAttributes_WhenUserIsLoggedIn() {

        String firstName = "test";
        String email = "test@gmail.com";

        when(userService.getLoggedInUserFirstName()).thenReturn(firstName);
        when(userService.getLoggedInUserEmail()).thenReturn(email);

        String viewName = homeController.home(model);

        assertEquals("index", viewName);
        verify(model).addAttribute("loggedInUser", firstName);
        verify(model).addAttribute("loggedInUserEmail", email);
    }

    @Test
    void home_ShouldAddNullAttributes_WhenUserIsNotLoggedIn() {

        when(userService.getLoggedInUserFirstName()).thenReturn(null);
        when(userService.getLoggedInUserEmail()).thenReturn(null);

        String viewName = homeController.home(model);

        assertEquals("index", viewName);
        verify(model).addAttribute("loggedInUser", null);
        verify(model).addAttribute("loggedInUserEmail", null);
    }

    @Test
    void homeSuccess_ShouldAddLoggedInUserAttributes_WhenUserIsLoggedIn() {

        String firstName = "test";
        String email = "test@gmail.com";

        when(userService.getLoggedInUserFirstName()).thenReturn(firstName);
        when(userService.getLoggedInUserEmail()).thenReturn(email);

        String viewName = homeController.homeSuccess(model);

        assertEquals("index", viewName);
        verify(model).addAttribute("loggedInUser", firstName);
        verify(model).addAttribute("loggedInUserEmail", email);
    }

    @Test
    void homeSuccess_ShouldAddNullAttributes_WhenUserIsNotLoggedIn() {

        when(userService.getLoggedInUserFirstName()).thenReturn(null);
        when(userService.getLoggedInUserEmail()).thenReturn(null);

        String viewName = homeController.homeSuccess(model);

        assertEquals("index", viewName);
        verify(model).addAttribute("loggedInUser", null);
        verify(model).addAttribute("loggedInUserEmail", null);
    }
}