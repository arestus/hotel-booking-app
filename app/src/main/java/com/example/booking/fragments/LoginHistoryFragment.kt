package com.example.booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.databinding.FragmentChangeEmailBinding
import com.example.booking.databinding.FragmentLoginHistoryBinding

class LoginHistoryFragment : Fragment() {
    private var _binding: FragmentLoginHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginHistoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.loginHistoryTitle.text = "Login history"
        binding.loginHistoryBack.setOnClickListener{
            findNavController().navigate(R.id.action_changeEmailFragment_to_profileFragment)
        }
//        return inflater.inflate(R.layout.fragment_login_history, container, false)
        return binding.root
    }


}