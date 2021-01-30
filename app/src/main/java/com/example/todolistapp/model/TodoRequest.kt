package com.example.todolistapp.model

data class TodoRequest(
    val description: String,
    val email: String,
    val password: String,
    val time: String,
    val title: String
)