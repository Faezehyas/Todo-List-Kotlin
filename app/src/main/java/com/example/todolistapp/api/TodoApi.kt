package com.example.todolistapp.api

import com.example.todolistapp.model.TodoResponse
import com.example.todolistapp.model.TodoRequest
import com.example.todolistapp.util.Constants.TASK
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TodoApi {

    @GET(TASK)
    suspend fun getTodos() : Response<List<TodoResponse>>

    @POST(TASK)
    suspend fun createTodo(@Body todo: TodoRequest) : Response<TodoResponse>
}