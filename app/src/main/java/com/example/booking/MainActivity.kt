package com.example.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.booking.data.UserMin
import com.example.booking.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        val myApplication = application as BookingApp
        val httpApiService = myApplication.httpApiService

        CoroutineScope(Dispatchers.IO).launch {
            val user: UserMin = UserMin("alina@gmail.com", "qaqaqa")
            val decodedJsonResult = httpApiService.login(user)


            val userMax = decodedJsonResult

            withContext(Dispatchers.Main){
                Log.d("HttpString", "$userMax")
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