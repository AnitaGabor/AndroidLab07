package com.example.quizapp.model

import com.google.gson.annotations.SerializedName

data class Responde(val response_code: Int, val results: MutableList<Question> )

data class Question(
    val category: String,
    val type: String ,
    @SerializedName("question")
    val text: String,
    @SerializedName("incorrect_answers")
    val answers: MutableList<String>,
    @SerializedName("correct_answer")
    val correctAnswer: String ){


}



var numberOfCorrectAnswer = 0

