package com.example.todolistapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://192.168.1.1/3000/api/"


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { intercepterChain ->
            val original = intercepterChain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)

            val request = requestBuilder.build()
            intercepterChain.proceed(request)
        }.build()

    val instance : Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}

//if you wanna add auth token to the request, use .addHeader in line 18