package com.testing.controller;

import com.testing.model.File;
import com.testing.service.FileService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService service;

    @GetMapping
    public String index() {
        return "file-upload";
    }

    @GetMapping("all")
    public String allFile(Model model) {
        model.addAttribute("files", service.findAll());
        return "files";
    }

    @GetMapping("{id}")
    public String file(@PathVariable String id, Model model) {
        model.addAttribute("file", service.findById(id).get());
        return "file";
    }

    @PostMapping("add")
    public String saveFile(@RequestParam("title") String title,
                           @RequestParam("file") MultipartFile file,
                           Model model) throws IOException {

        final Binary binary = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        final File fileEntity = new File(title, binary, file.getContentType());

        System.out.println(file.getContentType());

        service.save(fileEntity);
        model.addAttribute("files", service.findAll());
        return "redirect:/file/all?file-added=true";
    }

    @GetMapping("download/{id}")
    public ResponseEntity downloadFile(@PathVariable String id) {
        final File file = service.findById(id).get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + file.getTitle()
                                + "." + file.getType().substring(6) + "\"")
                .body(file.getFile().getData());
    }

    @GetMapping("delete/{id}")
    public String deleteFile(@PathVariable String id, Model model) {
        service.deleteById(id);
        model.addAttribute("files", service.findAll());
        return "redirect:/file/all?file-deleted=true";
    }
}
