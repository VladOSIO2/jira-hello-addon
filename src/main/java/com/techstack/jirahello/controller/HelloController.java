package com.techstack.jirahello.controller;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.atlassian.connect.spring.ContextJwt;
import com.atlassian.connect.spring.IgnoreJwt;
import com.techstack.jirahello.dto.GenerationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public ModelAndView helloWorld(@AuthenticationPrincipal AtlassianHostUser hostUser) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("authentication", String.valueOf(hostUser));
        return modelAndView;
    }

    @GetMapping("/styles")
    @IgnoreJwt
    public ResponseEntity<String> css() {
        String css = """
                .yellow-button {
                    background-color: yellow;
                }
                """;
        return ResponseEntity.ok(css);
    }

    @GetMapping("/generate-test-case")
    public ModelAndView getGenerationForm(
            @ModelAttribute("generationRequest") GenerationRequest generationRequest,
            @AuthenticationPrincipal Object authentication) {

        System.out.println(authentication);

        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("atlassianConnectToken", authentication);
        modelAndView.setViewName("generation");
        return modelAndView;
    }

    @PostMapping("/generate-test-case")
    @ContextJwt
    public ModelAndView post(
            @ModelAttribute("generationRequest") GenerationRequest generationRequest,
            @AuthenticationPrincipal AtlassianHostUser hostUser

    ) {
        System.out.println(hostUser);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject("authentication", String.valueOf(hostUser));
        return modelAndView;
    }
}
