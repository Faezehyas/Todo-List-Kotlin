package com.example.todolistapp.api

import com.example.todolistapp.model.LoginRequest
import com.example.todolistapp.model.LoginResponse
import com.example.todolistapp.model.RegisterRequest
import com.example.todolistapp.model.RegisterResponse
import com.example.todolistapp.util.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interface for defining REST request functions
 */
interface RegisterLoginApi {

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST(Constants.REGISTER_URL)
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

}
