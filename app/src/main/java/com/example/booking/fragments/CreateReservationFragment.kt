package com.example.booking.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.booking.BookingApp
import com.example.booking.MainActivity
import com.example.booking.R
import com.example.booking.data.ReservationMin
import com.example.booking.data.UserMin
import com.example.booking.databinding.FragmentCreateReservationBinding
import com.example.booking.databinding.FragmentSpecificHotelBinding
import com.example.booking.viewmodels.HotelViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CreateReservationFragment : Fragment() {
    private val args by navArgs<CreateReservationFragmentArgs>()

    private var _binding: FragmentCreateReservationBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: HotelViewModel


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("test_args", "$args")
        _binding = FragmentCreateReservationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        mUserViewModel = ViewModelProvider(this)[HotelViewModel::class.java]

        var days = 0
        binding.minusView.isEnabled =false
        binding.confirmReservation.isEnabled =false

        binding.addView.setOnClickListener {
            days++
            if (days > 0) {
                binding.minusView.isEnabled =true
                binding.confirmReservation.isEnabled =true
            }
            updateData(days)
        }

        binding.minusView.setOnClickListener {
            if (days > 0) {
                days--
                if (days == 0) {
//                    Log.d("TEST", "$days")
                    binding.minusView.isEnabled =false
                    binding.confirmReservation.isEnabled =false
                }
            }
            updateData(days)

        }
        (requireActivity() as MainActivity).supportActionBar?.title = args.createReserve.name
        updateData(days)
        Picasso.get().load(args.createReserve.url).into(binding.hotelPhoto)

        binding.confirmReservation.setOnClickListener {
            val myApplication = activity?.application as BookingApp
            val httpApiService = myApplication.httpApiService

            try {
                CoroutineScope(Dispatchers.Main).launch {
                    val test = httpApiService.createReservation(ReservationMin(args.createReserve.id, days))
                    withContext(Dispatchers.Main) {
//                        Log.d("BookingDBString", "$test")
                        findNavController().navigate(R.id.action_createReservationFragment_to_myReservationFragment)
                    }
                }
            } catch (e: Exception) {

            }

        }
        binding.createReservTitle.text = args.createReserve.name
        binding.specificHotelBack.setOnClickListener {
            findNavController().navigate(R.id.action_createReservationFragment_to_hotelsListFragment)
        }


        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateData(days: Int) {
        binding.daysView.text = HtmlCompat.fromHtml(
            "Days: <b>${days}<b>",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        binding.priceView.text = HtmlCompat.fromHtml(
            "Price: <b>${args.createReserve.pricePerNight * days}$<b>",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }


}