package com.study.crud.entity

import javax.persistence.*

@Entity
class Document(
    title: String,
    content: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var title = title
        protected set

    var content = content
        protected set

    fun update(title: String, content: String): Document{
        this.title = title
        this.content = content
        return this
    }
}