package com.example.todolistapp.model

data class RegisterResponse(
    val token: String,
    val user: User
)