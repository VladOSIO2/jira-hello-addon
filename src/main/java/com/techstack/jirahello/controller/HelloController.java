package com.techstack.jirahello.controller;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.techstack.jirahello.dto.GenerationRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/generate-test-case")
    public ModelAndView getGenerationForm(@ModelAttribute("generationRequest") GenerationRequest generationRequest) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("generation");
        return modelAndView;
    }
}
