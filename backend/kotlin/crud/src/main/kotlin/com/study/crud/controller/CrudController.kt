package com.study.crud.controller

import com.study.crud.entity.Document
import com.study.crud.payload.request.DocumentRequest
import com.study.crud.payload.response.DocumentListResponse
import com.study.crud.payload.response.DocumentResponse
import com.study.crud.service.CrudService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

class CrudController(
    private val crudService: CrudService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/document")
    fun createDocument(@RequestBody request: DocumentRequest): Document {
        return crudService.createDocument(request);
    }

    @GetMapping("/document/{document-id}")
    fun getOneDocument(@PathVariable("document-id") documentId: Long): DocumentResponse {
        return crudService.readOneDocument(documentId);
    }

    @GetMapping("/document")
    fun getAllDocument(@PathVariable("document-id") documentId: Long): DocumentListResponse {
        return crudService.readAllDocument(documentId);
    }

    @PatchMapping("/document/{document-id}")
    fun patchDocument(@PathVariable("document-id") documentId: Long, @RequestBody request: DocumentRequest): DocumentResponse {
        return crudService.updateDocument(documentId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/document/{document-id}")
    fun patchDocument(@PathVariable("document-id") documentId: Long) {
        return crudService.deleteDocument(documentId);
    }

}