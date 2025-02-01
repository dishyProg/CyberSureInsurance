package com.insurance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String renderIndex() {
        return "/pages/home/index";
    }

    @GetMapping("/ai-assistance")
    public String renderAiAssistance() {
        return "/pages/home/ai-assistance";
    }
}
