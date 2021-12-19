package com.example.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

import androidx.navigation.ui.setupWithNavController
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator

import com.example.booking.databinding.ActivityMainBinding
import com.example.booking.viewmodels.ImageStorageManager
import com.squareup.picasso.Picasso
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var session: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        session = SessionManager(applicationContext)
        //val del = deleteDatabase("BookingDataBase")

        drawerLayout = findViewById(R.id.drawer_layout)
        navController = findNavController(R.id.fragmentContainerView)
        binding.navigationView.setupWithNavController(navController)
        supportActionBar!!.hide()

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }



    fun openCloseNavigationDrawer(view: View) {
        session = SessionManager(applicationContext)
        findViewById<TextView>(R.id.userEmail).text = (session).getEmail()
        val yourFilePath = applicationContext.filesDir.toString() + "/" + "profile"
        val yourFile = File(yourFilePath)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
            var drawerAvatar = findViewById<ImageView>(R.id.userPhoto)
            var userEmail = findViewById<TextView>(R.id.userEmail)
            if (!yourFile.isFile) {
                Picasso.get()
                    .load("https://brokenfortest")
                    .resize(150, 150)
                    .placeholder(
                        AvatarGenerator.avatarImage(
                            this.applicationContext,
                            200,
                            AvatarConstants.CIRCLE,
                            userEmail.text.toString()
                        )
                    ).into(drawerAvatar)
//            var currentAvatar = ImageStorageManager.getImageFromInternalStorage(this.applicationContext, "profile")
//            drawerAvatar.setImageBitmap(currentAvatar)
            } else {
                val currentPhoto = ImageStorageManager.getImageFromInternalStorage(
                    this.applicationContext,
                    "profile"
                )
                drawerAvatar.setImageBitmap(currentPhoto)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}