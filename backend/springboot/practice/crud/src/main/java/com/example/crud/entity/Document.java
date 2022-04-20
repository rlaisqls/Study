package com.example.crud.entity;

import com.example.crud.dto.CreateDocumentDto;
import com.example.crud.dto.UpdateDocumentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String contents;

    public Document(CreateDocumentDto documentDto) {
        title = documentDto.getTitle();
        contents = documentDto.getContents();
    }

}
