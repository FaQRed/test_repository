package com.example.library.library.controller;

import com.example.library.library.service.BookService;
import com.example.library.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigationController {

    private UserService userService;
    private BookService bookService;

    @Autowired
    public NavigationController(UserService userService, BookService bookService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/user";
    }

    @GetMapping("/references")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'LIBRARIAN')")
    public String references() {
        return "references/references";
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String statistics() {
        return "statistics";
    }

    @GetMapping("/library")
    @PreAuthorize("hasAuthority('USER')")
    public String books(Model model){
        model.addAttribute("books", bookService.getAllBooks());
    return "references/userPage/library";
    }
}