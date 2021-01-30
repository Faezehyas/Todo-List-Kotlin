package com.example.todolistapp.model

data class TodoResponse(
    val __v: Int,
    val _id: String,
    val completed: Boolean,
    val createdAt: String,
    val description: String,
    val owner: String,
    val time: String,
    val title: String,
    val updatedAt: String
)