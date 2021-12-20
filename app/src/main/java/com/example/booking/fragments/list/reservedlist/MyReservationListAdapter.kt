package com.example.booking.fragments.list.reservedlist

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.BookingApp
import com.example.booking.HttpApiService
import com.example.booking.MainActivity
import com.example.booking.R
import com.example.booking.data.BookingDatabase
import com.example.booking.data.Reservation
import com.example.booking.databinding.ReservationRowCustomBinding
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


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = reservationList[position]
        with(holder) {
            with(currentItem) {
                binding.hotelName.text = name

                Picasso.get().load(
                    url
                ).into(binding.hotelPhoto)

                binding.reservationDate.text ="Date:\n${getDateTime(dateTimestamp)}"
                binding.nightsReserved.text = "${nightsCount} nights"
                binding.price.text = "Total cost: ${pricePerNight * nightsCount}$"
                binding.deleteButton.setOnClickListener { view ->
                    val myApplication = view.context.applicationContext as BookingApp
                    val httpApiService = myApplication.httpApiService
                    val dao = BookingDatabase.getInstance(view.context).bookingDao()

                    val builderAlert = AlertDialog.Builder(view.context)
                    builderAlert.setPositiveButton("Yes") { _, _ ->
                        CoroutineScope(Dispatchers.IO).launch {
                            httpApiService.deleteReservation(reservationId)
                            dao.delReservation()

                            withContext(Dispatchers.Main) {
                                val reservations = httpApiService.getReservations().reservations?.toTypedArray() ?: emptyArray()
                                dao.insertAllReservations(*reservations)
                            }

                        }
                        CoroutineScope(Dispatchers.IO).launch {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                            view.context,
                            "The reservation at the $name was successfully canceled",
                            Toast.LENGTH_LONG
                        ).show()
                                val action =
                                    MyReservationFragmentDirections.actionMyReservationFragmentSelf()
                                Navigation.findNavController(view).navigate(action)
                            }
                        }

//                        mUserViewModel.deleteUser(args.curentUser)

//                        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                    }
                    builderAlert.setNegativeButton("No") { _, _ -> }
                    builderAlert.setTitle("Delete  reservation in ${name}?")
                    builderAlert.setMessage("Are you sure you want to cancel the hotel reservation?")
                    builderAlert.create().show()








                }
            }

        }
    }

    private fun getDateTime(Timestamp: String): String? {
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
        RecyclerView.ViewHolder(binding.root) {

    }
}
