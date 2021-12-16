package com.example.booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.databinding.FragmentHotelsListBinding
import com.example.booking.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.backButtonProfile.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_fragment_hotels_list)
        }

        //return inflater.inflate(R.layout.fragment_profile, container, false)
        return binding.root
    }




}