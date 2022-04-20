package com.example.crud.service;

import com.example.crud.dto.CreateDocumentDto;
import com.example.crud.dto.UpdateDocumentDto;
import com.example.crud.entity.Document;
import com.example.crud.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@ComponentScan(basePackages = {"com.example.crud.repository"})
public class TestService {
    @Autowired
    DocumentRepository documentRepository;

    public void createDocument(CreateDocumentDto documentDto) {
        Document document = new Document(documentDto);
        documentRepository.save(document);
    }

    public void deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);
    }

//    public void updateDocument(Long documentId, UpdateDocumentDto documentDto){
//        Optional<Document> byId = documentRepository.findById(1L);
//        Document document = byId.get();
//        document.setTitle(documentDto.getTitle());
//        document.setContents(documentDto.getContents());
//        documentRepository.save(document);
//    }
}
