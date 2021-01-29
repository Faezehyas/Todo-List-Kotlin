package com.example.todolistapp.responses

data class LoginResponse(
    val _id: String,
    val email: String,
    val name: String,
    val token: String,
    val username: String
)