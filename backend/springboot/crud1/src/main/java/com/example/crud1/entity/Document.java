package com.example.crud1.entity;

import com.example.crud1.dto.CreateDocumentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String title;

    private String contents;

    public Document(CreateDocumentDto documentDto){
        this.title = documentDto.getTitle();
        this.contents = documentDto.getContents();
    }
}
