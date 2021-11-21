package com.example.quizapp.repository

import com.example.quizapp.api.RetrofitInstance
import com.example.quizapp.model.Question
import com.example.quizapp.model.Responde

class Repository {

    suspend fun getPost(): Responde{
        return RetrofitInstance.api.getPost()
    }
}