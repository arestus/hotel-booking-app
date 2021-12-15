package com.example.booking


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.booking.data.*

import com.example.booking.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        val dao = BookingDatabase.getInstance(this).bookingDao()



        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//      setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))


    }

    private fun FillLocalDB(): BookingDao {
        val myApplication = application as BookingApp
        val httpApiService = myApplication.httpApiService

        val dao = BookingDatabase.getInstance(this).bookingDao()

        CoroutineScope(Dispatchers.IO).launch {

            //val deleteDB = deleteDatabase("BookingDB");
            var user = httpApiService.login(UserMin("alina@gmail.com", "qaqaqa"))
            val hotels = httpApiService.getHotels().hotels?.toTypedArray() ?: emptyArray()
            val reservations = httpApiService.getReservations().reservations?.toTypedArray() ?: emptyArray()
            val localUsers = httpApiService.getLocalUsers().users?.toTypedArray() ?: emptyArray()
            val loginHistory = httpApiService.getLoginHistory().loginEntries?.toTypedArray() ?: emptyArray()
            if (!hotels.isNullOrEmpty())
                dao.insertAllHotels(*hotels)
            if (user != null)
                dao.insertUser(user)
            if (!reservations.isNullOrEmpty() && user != null && !hotels.isNullOrEmpty())
                dao.insertAllReservations(*reservations)
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


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}