package com.example.booking.fragments

import android.app.ProgressDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.navigation.fragment.findNavController
import com.example.booking.BookingApp
import com.example.booking.R
import com.example.booking.SessionManager
import com.example.booking.data.BookingDao
import com.example.booking.data.BookingDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.schedule


class SplashScreenFragment : Fragment() {
    private val loading : ProgressBar? = null
    private lateinit var session: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        session = SessionManager(requireActivity())
        var isLogged = (session as SessionManager).isLoggedIn()

        //timer to switch fragments
        Timer("Test", false).schedule(2500) {
            requireActivity().runOnUiThread {

                if (isLogged)
                {
                    FillLocalDB()
                    findNavController().navigate(R.id.action_splashScreenFragment_to_hotelsListFragment)
                }
                else
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }}


        return view
    }

    fun FillLocalDB(): BookingDao {
        val myApplication = activity?.application as BookingApp
        val httpApiService = myApplication.httpApiService

        val dao = BookingDatabase.getInstance(requireActivity()).bookingDao()

        CoroutineScope(Dispatchers.IO).launch {

            val hotels = httpApiService.getHotels().hotels?.toTypedArray() ?: emptyArray()
            val reservations = httpApiService.getReservations().reservations?.toTypedArray() ?: emptyArray()
            val localUsers = httpApiService.getLocalUsers().users?.toTypedArray() ?: emptyArray()
            val loginHistory = httpApiService.getLoginHistory().loginEntries?.toTypedArray() ?: emptyArray()
            if (!hotels.isNullOrEmpty())
                dao.insertAllHotels(*hotels)
            if (!reservations.isNullOrEmpty() && !hotels.isNullOrEmpty()){
                dao.delReservation()
                dao.insertAllReservations(*reservations)
            }
            if (!localUsers.isNullOrEmpty())
                dao.insertAllLocalUsers(*localUsers)
            if (!loginHistory.isNullOrEmpty())
                dao.insertAllLoginHistory(*loginHistory)

            withContext(Dispatchers.Main){
                Log.d("BookingDBString", "DB Filled")
            }
        }
        return dao;
    }


}