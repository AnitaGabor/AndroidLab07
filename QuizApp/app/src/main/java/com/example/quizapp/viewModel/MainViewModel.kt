package com.example.quizapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.Question
import com.example.quizapp.model.Responde
import com.example.quizapp.model.numberOfCorrectAnswer
import com.example.quizapp.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    var currentPosition: Int = 0
    private var  userName:String = ""
    var points:Int = 0
    val myResponse: MutableLiveData<Responde> = MutableLiveData()
    init{

        getPost()
    }

    fun getPost()
    {
        viewModelScope.launch {
            try {
                val result = repository.getPost()
                myResponse.value = result
                Log.d("MainViwemodel ok", "ListViewModel - #questions:  ${result.response_code}")
            }catch(e: Exception){
                Log.d("MainViewModel fail", "ListViewModel exception: ${e.toString()}")
            }
        }
    }
    fun pointInProfile() : Int
    {
        if(points < numberOfCorrectAnswer)
            points = numberOfCorrectAnswer
        return points
    }
    fun setName(name :String)
    {
        userName = name
    }
    fun getName() :String
    {
        return userName
    }
    fun addQuestion(question: Question)
    {
        myResponse.value?.results?.add(question)
    }
    fun getQuestion() : Question?
    {
        return myResponse.value?.results?.get(currentPosition)
    }

}