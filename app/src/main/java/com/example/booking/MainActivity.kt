package com.example.booking


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.booking.data.Hotel
import com.example.booking.data.LocalUser
import com.example.booking.data.LoginHistory

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

        val myApplication = application as BookingApp
        val httpApiService = myApplication.httpApiService

        CoroutineScope(Dispatchers.IO).launch {


           /* val call: Call<LoginHistory> = httpApiService.getLoginHistory()
            call.enqueue(object : Callback<LoginHistory> {
                override fun onResponse(call: Call<LoginHistory>, response: Response<LoginHistory>) {
                    if (response.isSuccessful) {
                        val rawJsonString = response.body()?.toString()
                        Log.d("HttpString", "Success")
                    }
                }

                override fun onFailure(call: Call<LoginHistory>, t: Throwable) {
                    Log.d("HttpString", "FAIL")
                }

            })*/
            withContext(Dispatchers.Main){
                Log.d("HttpString", "Check")
            }
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//      setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))


    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}