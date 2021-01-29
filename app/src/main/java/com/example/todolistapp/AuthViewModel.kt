package com.example.todolistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.network.Resource
import com.example.todolistapp.repository.AuthRepository
import com.example.todolistapp.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
        private val repository : AuthRepository
) : ViewModel() {

    private val _loginResponse
    val loginResponse : LiveData<Resource<LoginResponse>>

    suspend fun login( username : String, password : String,) = viewModelScope.launch {
        repository.login(username, password)
    }

}