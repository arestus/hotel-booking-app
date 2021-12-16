package com.example.booking.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.booking.MainActivity
import com.example.booking.R
import com.example.booking.databinding.FragmentLoginBinding
import com.example.booking.databinding.FragmentSpecificHotelBinding
import com.example.booking.viewmodels.HotelViewModel
import com.squareup.picasso.Picasso

class SpecificHotelFragment : Fragment() {

    private val args by navArgs<SpecificHotelFragmentArgs>()

    private var _binding: FragmentSpecificHotelBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: HotelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpecificHotelBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        mUserViewModel = ViewModelProvider(this)[HotelViewModel::class.java]
//
        (requireActivity() as MainActivity).supportActionBar?.title = args.currentHotel.name
        R.string.fragment_specific_hotel
        binding.reservedByPeople.text = args.currentHotel.reservedByPeople.toString()
        binding.pricePerRoom.text = args.currentHotel.pricePerNight.toString()
        binding.roomLeft.text = args.currentHotel.roomsLeft.toString()
        Picasso.get().load(args.currentHotel.url).into( binding.hotelPhoto)
//        binding.UpdateButton.setOnClickListener {
//            updateData()
//        }
        binding.hotelNameTitle.text = args.currentHotel.name
        binding.specificHotelBack.setOnClickListener{
            findNavController().navigate(R.id.action_specificHotelFragment_to_hotelsListFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
//         val urltmp = "https://www.gannett-cdn.com/-mm-/f85d715f4ef18e245988237e803cdef2db1daf68/c=0-255-4256-2656/local/-/media/USATODAY/test/2013/10/25/1382729502000-160857233.jpg"
//        Picasso.get().load(urltmp).into( binding.hotelPhoto)






//        return binding.root
    }


}