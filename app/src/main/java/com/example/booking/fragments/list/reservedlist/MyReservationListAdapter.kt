package com.example.booking.fragments.list.reservedlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.BookingApp
import com.example.booking.R
import com.example.booking.data.BookingDatabase
import com.example.booking.data.Reservation
import com.example.booking.data.ReservationMin
import com.example.booking.databinding.ReservationRowCustomBinding
import com.example.booking.fragments.list.hotelslist.HotelsListFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MyReservationListAdapter(private var reservationList: List<Reservation>) :
    RecyclerView.Adapter<MyReservationListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ReservationRowCustomBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = reservationList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int, ) {
        val myApplication : BookingApp? =null

        val httpApiService = myApplication?.httpApiService
        val currentItem = reservationList[position]
        with(holder) {
            with(currentItem) {
//                getDateTime(dateTimestamp)
                binding.hotelName.text = name
                Picasso.get().load(
                    url
                ).into(binding.hotelPhoto)
                binding.reservationDate.text = "Date:${reservationId}\n${getDateTime(dateTimestamp)}"
                binding.nightsReserved.text = "${nightsCount} nights ${reservationList.size}"
                binding.price.text = "Total cost: ${pricePerNight * nightsCount}$"
                binding.deleteButton.setOnClickListener { view ->

                        CoroutineScope(Dispatchers.Main).launch {
                            val test = httpApiService?.deleteReservation(13)
                            withContext(Dispatchers.Main) {
                                Log.d("BookingDBString", "$reservationId")
                                Log.d("BookingDBString", "$test")
                                val action = MyReservationFragmentDirections.actionMyReservationFragmentSelf()
                                Navigation.findNavController(view).navigate(action)
                            }

                        }



                }

            }

        }
    }

//    fun cancelReservation(){
//
//        try {
//            CoroutineScope(Dispatchers.Main).launch {
//                val test = httpApiService.createReservation(ReservationMin(args.createReserve.id, days))
//                withContext(Dispatchers.Main) {
//                    Log.d("BookingDBString", "$test")
//                    findNavController().navigate(R.id.action_createReservationFragment_to_myReservationFragment)
//                }
//
//            }
//
//        } catch (e: Exception) {
//
//        }
//    }

    private fun getDateTime(Timestamp: String): String? {
//        val Timestamp: Long = 1633304782
        val timeD = Date(Timestamp.toLong())
        val sdf = SimpleDateFormat("dd/MMM/yyyy")
        val Time = sdf.format(timeD)
        return Time
    }

    fun setData(user: List<Reservation>) {
        this.reservationList = user
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ReservationRowCustomBinding) :
        RecyclerView.ViewHolder(binding.root){

        }
}
