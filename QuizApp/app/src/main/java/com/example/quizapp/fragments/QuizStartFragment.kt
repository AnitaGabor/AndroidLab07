package com.example.quizapp.fragments

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizStartBinding
import com.example.quizapp.repository.Repository
import com.example.quizapp.viewModel.MainViewModel
import com.example.quizapp.viewModel.MainViewModelFactory
import com.example.quizapp.viewModel.SharedViewModel

class QuizStartFragment : Fragment() {
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

        val binding: FragmentQuizStartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_start, container, false)
        val getContactList = registerForActivityResult(
            ActivityResultContracts.PickContact(),
            ActivityResultCallback {
                val list = listOf(ContactsContract.Contacts.DISPLAY_NAME).toTypedArray()
                val cursor = activity?.contentResolver?.query(it, list, null, null, null)
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        binding.editTextTextPersonName.setText(cursor.getString(0))
                    }
                    cursor.close()
                }
            })

        binding.button.setOnClickListener {
            getContactList.launch(null)
        }
        binding.button2.setOnClickListener{

            viewModel.setName(binding.editTextTextPersonName.text.toString())

            this.findNavController().navigate(R.id.action_quizStartFragment_to_questionFragment)
        }

        return binding.root

    }


}