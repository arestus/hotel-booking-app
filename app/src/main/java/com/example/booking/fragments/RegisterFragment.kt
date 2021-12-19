package com.example.booking.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.booking.BookingApp
import com.example.booking.R
import com.example.booking.SessionManager
import com.example.booking.data.User
import com.example.booking.data.UserMin
import com.example.booking.databinding.FragmentLoginBinding
import com.example.booking.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.*
import kotlin.concurrent.schedule


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var session: SharedPreferences

    @SuppressLint("WrongConstant")
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

            val userEmail = binding.outlinedTextFieldEmail.editText?.text.toString()
            val userPassword = binding.outlinedTextFieldPassword.editText?.text.toString()

            val maskedEmail =
                if (userEmail.isNullOrEmpty()) ""
                else userEmail.lowercase()

            val maskedPassword =
                if (userPassword.isNullOrEmpty()) ""
                else userPassword

            if (userEmail.isEmailValid())
            CoroutineScope(Dispatchers.IO).launch {

                val user: UserMin = UserMin(maskedEmail, maskedPassword)
                var failedLogin: Int = 200

                try {
                    httpApiService.register(user)
                } catch (e: HttpException) {
                    failedLogin = e.code()
                } finally {
                    withContext(Dispatchers.Main) {
                        Timer("Test", false).schedule(2000) {
                            requireActivity().runOnUiThread {
                                binding.progressBar.visibility = View.INVISIBLE
                                binding.progressBarBackground.visibility = View.INVISIBLE
                                if (failedLogin == 200) {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        withContext(Dispatchers.Main) {
                                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        myApplication,
                                        "Oops! Sorry, something went wrong!",
                                        3000
                                    ).show()
                                    findNavController().navigate(R.id.action_registerFragment_self)
                                }
                            }
                        }
                    }
                }
            } else
                Toast.makeText(
                    myApplication,
                    "Incorrect Email input. Try again!",
                    2000
                ).show()
        }
        return binding.root
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

}