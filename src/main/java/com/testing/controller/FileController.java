package com.testing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("file")
public class FileController {

    @GetMapping
    public String index() {
        return "file-upload";
    }

}
