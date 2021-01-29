package com.example.todolistapp.repository

import com.example.todolistapp.network.AuthApi

class AuthRepository (private val api : AuthApi) : BaseRepository() {

    suspend fun login( username : String, password : String) = safeApiCall {
        api.login(username, password)
    }
}