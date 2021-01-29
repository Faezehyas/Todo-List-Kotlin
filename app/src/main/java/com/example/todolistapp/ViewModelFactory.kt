package com.example.todolistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolistapp.repository.AuthRepository
import com.example.todolistapp.repository.BaseRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            //if we want new viewmodel instance, define it here
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}