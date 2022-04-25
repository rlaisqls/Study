package com.example.crud1.service;

import com.example.crud1.dto.CreateDocumentDto;
import com.example.crud1.entity.Document;
import com.example.crud1.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@ComponentScan(basePackages = {"com.example.crud1.repository"})
@Service
public class TestService {
    @Autowired
    DocumentRepository documentRepository;

    public void createDocument(CreateDocumentDto documentDto){
        Document document = new Document(documentDto);
        documentRepository.save(document);
    }
    public void deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);
    }

    public Optional<Document> readDocument(Long documentId){
        return documentRepository.findById(documentId);
    }
    public void updateDocument(Long documentId, CreateDocumentDto documentDto){
        Optional<Document> optionalDocument = documentRepository.findById(documentId);
        optionalDocument.ifPresent(selectDocument->{
            selectDocument.setTitle(documentDto.getTitle());
            selectDocument.setContents(documentDto.getContents());
            documentRepository.save(selectDocument);
        });
    }
}
