package com.example.booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booking.R
import com.example.booking.databinding.FragmentCreateReservationBinding
import com.example.booking.databinding.FragmentSpecificHotelBinding

class CreateReservationFragment : Fragment() {
    private var _binding: FragmentCreateReservationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.createReservTitle.text = "Create reservation"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_reservation, container, false)
    }


}