package ru.courceWork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping()
    public String view() {
        return "routes/index";
    }
}
