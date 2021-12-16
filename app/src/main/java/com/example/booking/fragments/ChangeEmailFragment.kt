package com.example.booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.databinding.FragmentChangeEmailBinding
import com.example.booking.databinding.FragmentMyResrvationBinding

class ChangeEmailFragment : Fragment() {
    private var _binding: FragmentChangeEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeEmailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.changeEmailBack.setOnClickListener{
            findNavController().navigate(R.id.action_loginHistoryFragment_to_profileFragment)
        }
        binding.changeEmailTitle.text = "Change email"
//        return inflater.inflate(R.layout.fragment_change_email, container, false)
        return binding.root
    }


}