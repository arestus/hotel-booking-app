package com.example.booking.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.BookingApp
import com.example.booking.R
import com.example.booking.data.UserMin
import com.example.booking.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.schedule

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val myApplication = activity?.application as BookingApp
        val httpApiService = myApplication.httpApiService
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding.textViewRegister.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBarBackground.visibility = View.VISIBLE
            Timer("Test", false).schedule(2000) {
                requireActivity().runOnUiThread {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.progressBarBackground.visibility = View.INVISIBLE

                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                }
            }


        }
        binding.textViewLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBarBackground.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {

                withContext(Dispatchers.Main){
                    Log.d("HttpString", "userMax")
//                    Log.d("HttpString", "$userreg")
                }
            }
            Timer("Test", false).schedule(2000) {
                requireActivity().runOnUiThread {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.progressBarBackground.visibility = View.INVISIBLE

                    findNavController().navigate(R.id.action_loginFragment_to_hotelsListFragment)
                }
            }


        }

        CoroutineScope(Dispatchers.IO).launch {
//            val user: UserMin = UserMin("alina@gmail.com", "qaqaqa")
            val decodedJsonResult = httpApiService.getHotels().hotels
//
//
            val userMax = decodedJsonResult
//
            withContext(Dispatchers.Main){
                Log.d("HttpString", "$userMax")
            }
        }


        return binding.root
    }


}