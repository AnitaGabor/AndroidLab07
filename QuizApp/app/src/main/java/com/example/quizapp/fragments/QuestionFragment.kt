package com.example.quizapp.fragments

import com.example.quizapp.model.Question
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuestionBinding
import com.example.quizapp.model.numberOfCorrectAnswer
import androidx.activity.OnBackPressedCallback


import android.app.AlertDialog

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.repository.Repository
import com.example.quizapp.viewModel.MainViewModel
import com.example.quizapp.viewModel.MainViewModelFactory


class QuestionFragment : Fragment() {

    lateinit var binding: FragmentQuestionBinding
    private  var numberOfQuestion:Int = 0
    lateinit var currentQuestion: Question
    private val numberOfQuestions = 10

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        randomizeQuestions()
        binding.navigationButton.setOnClickListener {
            if ( numberOfQuestion < numberOfQuestions) {
                processAnswer(it)
                setNextQusetion()
                if(numberOfQuestion == numberOfQuestions){
                    binding.navigationButton.text = "SUBMIT";
                }
            } else {
                if ( numberOfQuestion == numberOfQuestions) {
                    processAnswer(it)
                    it.findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)

                }
            }
        }

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val alertDialog: AlertDialog? = activity?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.setTitle("EXIT")
                    builder.setMessage("Are you sure you want to end this quiz?")
                    builder.apply {
                        setPositiveButton("Yes",
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                                findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)
                            })
                        setNegativeButton("No",
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()

                            })
                    }
                    builder.show()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }

    private fun showQuestion() {
        numberOfQuestion++
        val index = numberOfQuestion
        val questionTextStr = "$index. " + currentQuestion.text
        binding.questionView.text = questionTextStr
        binding.firstAnswer.text = currentQuestion.correctAnswer
        binding.secondAnswer.text = currentQuestion.answers[0]
        binding.thirdAnswer.text = currentQuestion.answers[1]
        binding.fourthAnswer.text = currentQuestion.answers[2]

        binding.radioGroup.clearCheck()
    }

    private fun setNextQusetion()
    {
        viewModel.myResponse.observe(viewLifecycleOwner){
            it.results[numberOfQuestion].answers.shuffle()
            currentQuestion = it.results[numberOfQuestion]
            showQuestion()
        }

    }
    private fun randomizeQuestions() {
        viewModel.myResponse.observe(viewLifecycleOwner){
            it.results.shuffle()
            setNextQusetion()
        }

    }

    private fun processAnswer(it: View?) {
        val selectedElement = binding.radioGroup.checkedRadioButtonId
        if (selectedElement == -1) {
            Toast.makeText(this.activity, "No answer given", Toast.LENGTH_SHORT).show()
        }
        var indexOfSelectedAnswer = 0
        when (selectedElement) {
            R.id.firstAnswer -> indexOfSelectedAnswer = 3
            R.id.secondAnswer -> indexOfSelectedAnswer = 0
            R.id.thirdAnswer -> indexOfSelectedAnswer = 1
            R.id.fourthAnswer -> indexOfSelectedAnswer = 2
        }
        if(indexOfSelectedAnswer == 3){
            ++numberOfCorrectAnswer
        }
    }


}