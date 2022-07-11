package com.study.crud.repository

import com.study.crud.entity.Document
import org.springframework.data.repository.CrudRepository

interface DocumentRepository : CrudRepository<Document, Long> {
    override fun findAll(): List<Document>
}