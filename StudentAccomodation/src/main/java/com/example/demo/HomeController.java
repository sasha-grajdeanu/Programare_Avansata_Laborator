package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /**
     * getting the main page
     */
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }
}
