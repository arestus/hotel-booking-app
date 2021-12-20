package com.example.booking.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.booking.BookingApp
import com.example.booking.R
import com.example.booking.SessionManager
import com.example.booking.data.UserEmail
import com.example.booking.databinding.FragmentChangeEmailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class ChangeEmailFragment : Fragment() {
    private var _binding: FragmentChangeEmailBinding? = null
    private val binding get() = _binding!!
    private lateinit var session: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChangeEmailBinding.inflate(inflater, container, false)
        session = SessionManager(requireActivity())
        // Inflate the layout for this fragment
        binding.changeEmailBack.setOnClickListener {
            findNavController().navigate(R.id.action_loginHistoryFragment_to_profileFragment)
        }
        binding.changeEmailTitle.text = "Change email"
//        return inflater.inflate(R.layout.fragment_change_email, container, false)
        binding.changeEmailBack.setOnClickListener {
            findNavController().navigate(R.id.action_changeEmailFragment_to_profileFragment)
        }
        val myApplication = activity?.application as BookingApp
        val httpApiService = myApplication.httpApiService

        binding.changeEmailConfirm.setOnClickListener {
            val inputEmail = binding.outlinedTextFieldEmail.editText?.text?.toString()
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBarBackground.visibility = View.VISIBLE
            Timer("Test", false).schedule(2000) {
                requireActivity().runOnUiThread {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.progressBarBackground.visibility = View.INVISIBLE

                    if (inputEmail!!.isEmailValid()) {
                        try {
                            CoroutineScope(Dispatchers.Main).launch {
                                httpApiService.changeEmail(UserEmail(inputEmail))
                                (session as SessionManager).editEmail(inputEmail)
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        myApplication,
                                        "Email successfully changed!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                myApplication,
                                "Email change has failed. Try again!",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            myApplication,
                            "Incorrect input. Try again!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }


        return binding.root
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

}