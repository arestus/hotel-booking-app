package com.example.booking.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booking.BookingApp
import com.example.booking.R
import com.example.booking.SessionManager
import com.example.booking.databinding.FragmentLoginBinding
import com.example.booking.databinding.FragmentRegisterBinding
import java.util.*
import kotlin.concurrent.schedule


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var session: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        val myApplication = activity?.application as BookingApp
        val httpApiService = myApplication.httpApiService

        session = SessionManager(requireActivity())

        binding.textViewRegisterMe.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBarBackground.visibility = View.VISIBLE
            Timer("Test", false).schedule(2000) {
                requireActivity().runOnUiThread {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.progressBarBackground.visibility = View.INVISIBLE
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }
        }
        return binding.root
    }


}