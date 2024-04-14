package com.graphapp.graphvisualizer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class RootController {
    @GetMapping
    public String index(Model model) {
        return "index";
    }


}
