package com.example.quizapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.quizButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_quizStartFragment)
        }

        binding.detailQuizButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_listFragment)
        }

        binding.addButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_addFragment)
         }

        return binding.root

    }


}