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
    ): View? {
        Log.d("test_args", "$args")
        _binding = FragmentCreateReservationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        mUserViewModel = ViewModelProvider(this)[HotelViewModel::class.java]

        var days = 0


        binding.addView.setOnClickListener {

            days++
            if (days > 0) {

                binding.minusView.iconTint = ColorStateList.valueOf(R.color.orange);

            }
            updateData(days)

        }

        binding.minusView.setOnClickListener {

            if (days > 0) {
                days--
                if (days == 0) {
                    Log.d("TEST", "$days")
                    binding.minusView.iconTint = ColorStateList.valueOf(R.color.white);
                }
            }
            updateData(days)

        }
//HtmlCompat.fromHtml("Price per room: <b> ${args.currentHotel.pricePerNight} <b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        (requireActivity() as MainActivity).supportActionBar?.title = args.createReserve.name
        updateData(days)

//        binding.daysView.text = HtmlCompat.fromHtml(
//            "Rooms left:<b> ${args.createReserve.roomsLeft} <b>",
//            HtmlCompat.FROM_HTML_MODE_LEGACY
//        )
//        binding.localAttraction.text = "Rooms left: ${args.currentHotel.roomsLeft}"
//        binding.localAttractionDescription.text = "Rooms left: ${args.currentHotel.roomsLeft}"
        Picasso.get().load(args.createReserve.url).into(binding.hotelPhoto)

        binding.confirmReservation.setOnClickListener {
            val myApplication = activity?.application as BookingApp
            val httpApiService = myApplication.httpApiService
            try {
                CoroutineScope(Dispatchers.Main).launch {
                    val test = httpApiService.createReservation(ReservationMin(1, 1))
                    withContext(Dispatchers.Main) {
                        Log.d("BookingDBString", "$test")

                    }

                }

            } catch (e: Exception) {

            }

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