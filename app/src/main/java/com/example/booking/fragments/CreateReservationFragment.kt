package com.example.booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.booking.MainActivity
import com.example.booking.R
import com.example.booking.databinding.FragmentCreateReservationBinding
import com.example.booking.databinding.FragmentSpecificHotelBinding
import com.example.booking.viewmodels.HotelViewModel
import com.squareup.picasso.Picasso

class CreateReservationFragment : Fragment() {
    private val args by navArgs<CreateReservationFragmentArgs>()

    private var _binding: FragmentCreateReservationBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: HotelViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateReservationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        mUserViewModel = ViewModelProvider(this)[HotelViewModel::class.java]


//HtmlCompat.fromHtml("Price per room: <b> ${args.currentHotel.pricePerNight} <b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        (requireActivity() as MainActivity).supportActionBar?.title = args.createReserve.name
        binding.reservedByPeople.text = "Already reserved by ${args.createReserve.reservedByPeople} people"
        binding.pricePerRoom.text = HtmlCompat.fromHtml("Price per room: <b> ${args.createReserve.pricePerNight}$ <b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.roomLeft.text = HtmlCompat.fromHtml("Rooms left:<b> ${args.createReserve.roomsLeft} <b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
//        binding.localAttraction.text = "Rooms left: ${args.currentHotel.roomsLeft}"
//        binding.localAttractionDescription.text = "Rooms left: ${args.currentHotel.roomsLeft}"
        Picasso.get().load(args.createReserve.url).into( binding.hotelPhoto)
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return binding.root
    }


}