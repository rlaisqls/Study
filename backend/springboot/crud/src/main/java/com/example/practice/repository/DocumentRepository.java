package com.example.practice.repository;

import com.example.practice.entity.Document;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
