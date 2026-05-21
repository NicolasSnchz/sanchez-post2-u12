package com.nicolassnchz.sanchezpost1u11.domain.model

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
)
