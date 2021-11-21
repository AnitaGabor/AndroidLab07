package com.example.quizapp.api

import com.example.quizapp.constants.Constants.Companion.GET_QUESTION_URL
import com.example.quizapp.model.Question
import com.example.quizapp.model.Responde
import retrofit2.Call
import retrofit2.http.GET





interface QuestionApi {
    @GET(GET_QUESTION_URL)
    suspend fun getPost(): Responde

}