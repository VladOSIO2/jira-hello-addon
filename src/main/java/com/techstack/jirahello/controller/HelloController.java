package com.techstack.jirahello.controller;

import com.atlassian.connect.spring.AtlassianHostUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

    @GetMapping("/hello-world")
    public ModelAndView helloWorld(@AuthenticationPrincipal AtlassianHostUser hostUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("authentication", String.valueOf(hostUser));
        return modelAndView;
    }
}
