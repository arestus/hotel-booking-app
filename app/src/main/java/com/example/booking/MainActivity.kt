package com.example.booking


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

import androidx.navigation.ui.setupWithNavController
import com.example.booking.data.Hotel
import com.example.booking.data.LocalUser
import com.example.booking.data.LoginHistory

import com.example.booking.data.*


import com.example.booking.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//      setContentView(R.layout.activity_main)


        drawerLayout = findViewById(R.id.drawer_layout)
        navController = findNavController(R.id.fragmentContainerView)
        binding.navigationView.setupWithNavController(navController)


        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        FillLocalDB()
//        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))



    }

    private fun FillLocalDB(): BookingDao {
        val myApplication = application as BookingApp
        val httpApiService = myApplication.httpApiService

        val dao = BookingDatabase.getInstance(this).bookingDao()
        CoroutineScope(Dispatchers.IO).launch {
            val test = dao.getHotels()
            Log.d("BookingDBString333", "$test")

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
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}