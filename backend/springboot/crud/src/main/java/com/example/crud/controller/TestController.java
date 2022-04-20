package com.example.crud.controller;

import com.example.crud.dto.CreateDocumentDto;
import com.example.crud.dto.UpdateDocumentDto;
import com.example.crud.entity.Document;
import com.example.crud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @PostMapping
    public void createUser(@RequestBody CreateDocumentDto documentDto) {
        testService.createDocument(documentDto);
    }

    @DeleteMapping("/delete/{document-id}")
    public void deleteDocument(@PathVariable("document-id") Long parameter) {
        testService.deleteDocument(parameter);
    }

//    @GetMapping("/update/{document-id}")
//    public void update(@PathVariable("document-id") Long parameter, @RequestBody UpdateDocumentDto documentDto) {
//        testService.updateDocument(parameter, documentDto);
//    }

}
