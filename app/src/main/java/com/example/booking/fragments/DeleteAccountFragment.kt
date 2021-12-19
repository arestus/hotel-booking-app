package com.example.booking.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.booking.BookingApp
import com.example.booking.R
import com.example.booking.SessionManager
import com.example.booking.databinding.FragmentChangeEmailBinding
import com.example.booking.databinding.FragmentDeleteAccountBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class DeleteAccountFragment : Fragment() {
    private var _binding: FragmentDeleteAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var session: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeleteAccountBinding.inflate(inflater, container, false)
        session = SessionManager(requireActivity())
        // Inflate the layout for this fragment
        binding.deleteAccountBack.setOnClickListener {
            findNavController().navigate(R.id.action_deleteAccountFragment_to_profileFragment)
        }
        binding.textViewAccountToDelete.text = (session as SessionManager).GetEmail()
        val myApplication = activity?.application as BookingApp
        val httpApiService = myApplication.httpApiService
        binding.deleteAccountConfirm.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBarBackground.visibility = View.VISIBLE
            Timer("Test", false).schedule(2000) {
                requireActivity().runOnUiThread {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.progressBarBackground.visibility = View.INVISIBLE
                    try {
                        CoroutineScope(Dispatchers.Main).launch {
                            httpApiService.deleteUser()
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    myApplication,
                                    "Account successfully deleted!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            (session as SessionManager).LogoutUser()
                            findNavController().navigate(R.id.action_deleteAccountFragment_to_registerFragment)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            myApplication,
                            "Account's deleting has failed. Try again or later!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }




        return binding.root
    }
}