package com.example.crud1.service;

import com.example.crud1.dto.CreateDocumentDto;
import com.example.crud1.entity.Document;
import com.example.crud1.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TestService {
    /*

    private final DocumentRepository documentRepository;
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
    }*/
}
