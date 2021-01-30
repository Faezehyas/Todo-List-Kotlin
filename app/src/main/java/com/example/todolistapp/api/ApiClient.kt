package com.example.todolistapp.api

import com.example.todolistapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Retrofit instance class
 */

class ApiClient {
    private lateinit var apiService: RegisterLoginApi

    fun getApiService(): RegisterLoginApi {

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            apiService = retrofit.create(RegisterLoginApi::class.java)
        }

        return apiService
    }

}