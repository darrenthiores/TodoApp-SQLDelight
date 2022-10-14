package com.darrenthiores.core.model.domain

data class TodoDomain(
    val id: Long = 0,
    val title: String,
    val description: String,
    val done: Boolean = false
)
