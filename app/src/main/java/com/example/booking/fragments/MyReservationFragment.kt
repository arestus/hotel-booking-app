package com.example.booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import com.example.booking.databinding.FragmentMyResrvationBinding
import com.example.booking.databinding.FragmentOtherUsersBinding

class MyReservationFragment : Fragment() {
    private var _binding: FragmentMyResrvationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyResrvationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.myReservationsTitle.text = "My reservations"
        binding.myReservationsBack.setOnClickListener{
            findNavController().navigate(R.id.action_myReservationFragment_to_hotelsListFragment)
        }
//        return inflater.inflate(R.layout.fragment_my_resrvation, container, false)
        return binding.root
    }


}