package com.example.todolistapp.api

import com.example.todolistapp.model.LoginResponse
import com.example.todolistapp.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("users")
    fun createUser(
        @Field ("email") email : String,
        @Field ("name") name : String,
        @Field ("username") username : String,
        @Field ("password") password : String
    ) : Call<RegisterResponse>


    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field ("username") username: String,
        @Field ("password") password: String
    ) : Call<LoginResponse>

}