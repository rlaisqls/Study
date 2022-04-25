package com.example.practice.service;

import com.example.practice.dto.CreateDocumentDto;
import com.example.practice.entity.Document;
import com.example.practice.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
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
        Optional<Document> optionalDocument = documentRepository.findById(documentId);
        return optionalDocument;
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
