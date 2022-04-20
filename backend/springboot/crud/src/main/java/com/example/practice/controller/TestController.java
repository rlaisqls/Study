package com.example.practice.controller;

import com.example.practice.dto.CreateDocumentDto;
import com.example.practice.entity.Document;
import com.example.practice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping
    public void createDocument(@RequestBody CreateDocumentDto documentDto) {
        testService.createDocument(documentDto);
    }

    @DeleteMapping("/{document-id}")
    public void deleteDocument(@PathVariable("document-id") Long parameter) {
        testService.deleteDocument(parameter);
    }

    @GetMapping("/{document-id}")
    public Optional<Document> readDocument(@PathVariable("document-id") Long parameter){
        return testService.readDocument(parameter);
    }

    @PutMapping("/{document-id}")
    public void updateDocument(@PathVariable("document-id") Long parameter, @RequestBody CreateDocumentDto documentDto){
        testService.updateDocument(parameter, documentDto);
    }

}
