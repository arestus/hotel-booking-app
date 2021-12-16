package com.example.booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.databinding.FragmentOtherUsersBinding
import com.example.booking.databinding.FragmentProfileBinding

class OtherUsersFragment : Fragment() {
    private var _binding: FragmentOtherUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtherUsersBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.otherUsersTitle.text = "Other users"
        binding.otherUsersBack.setOnClickListener{
            findNavController().navigate(R.id.action_otherUsersFragment_to_hotelsListFragment)
        }
//        return inflater.inflate(R.layout.fragment_other_users, container, false)
        return binding.root
    }


}