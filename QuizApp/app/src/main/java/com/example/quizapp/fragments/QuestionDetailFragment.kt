package com.example.quizapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.repository.Repository
import com.example.quizapp.viewModel.MainViewModel
import com.example.quizapp.viewModel.MainViewModelFactory


class QuestionDetailFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = MainViewModelFactory(Repository())
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_question_detail, container, false)

        viewModel.myResponse.observe(viewLifecycleOwner)
        {
            val questionItem: Question? = viewModel.getQuestion()
            val question: TextView = layout.findViewById(R.id.questionDetailView)
            val textView1: TextView = layout.findViewById(R.id.firstAnswerView)
            val textView2: TextView = layout.findViewById(R.id.secondAnswerView)
            val textView3: TextView = layout.findViewById(R.id.thirdAnswerView)
            val textView4: TextView = layout.findViewById(R.id.fourthAnswerView)

            question.setText(questionItem?.text)
            textView1.setText(questionItem?.correctAnswer)
            textView2.setText(questionItem?.answers?.get(0))
            textView3.setText(questionItem?.answers?.get(1))
            textView4.setText(questionItem?.answers?.get(2))
        }




        return layout
    }

}