package com.study.crud.service

import com.study.crud.entity.Document
import com.study.crud.payload.request.DocumentRequest
import com.study.crud.payload.response.DocumentListResponse
import com.study.crud.payload.response.DocumentResponse
import com.study.crud.repository.DocumentRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class CrudService(
    private val documentRepository: DocumentRepository
) {
    fun createDocument(request: DocumentRequest): Document {
        val document = Document(
            title = request.title,
            content = request.content
        )

        return documentRepository.save(document)
    }

    fun readOneDocument(id: Long): DocumentResponse {
        val document: Document = documentRepository.findByIdOrNull(id) ?: throw IllegalStateException();

        return DocumentResponse(
            title = document.title,
            content = document.content
        )
    }

    fun readAllDocument(documentId: Long): DocumentListResponse {
        val list: List<DocumentResponse> = documentRepository.findAll()
            .map {
                DocumentResponse(
                    title = it.title,
                    content = it.content
                )
            }
            .toList()

        return DocumentListResponse(
            documentList = list
        );
    }

    fun updateDocument(id: Long, request: DocumentRequest): DocumentResponse {
        val document: Document = documentRepository.findByIdOrNull(id) ?: throw IllegalStateException();

        document.update(
            title = request.title,
            content = request.content
        )

        return DocumentResponse(
            title = document.title,
            content = document.content
        )
    }

    fun deleteDocument(id: Long) {
        val document: Document = documentRepository.findByIdOrNull(id) ?: throw IllegalStateException();

        documentRepository.delete(document)
    }
}