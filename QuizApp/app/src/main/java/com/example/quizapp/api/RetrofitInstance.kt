package com.example.quizapp.api

import com.example.quizapp.constants.Constants.Companion.BASE_URL

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val api:QuestionApi by lazy {
        retrofit.create(QuestionApi :: class.java)
    }

}