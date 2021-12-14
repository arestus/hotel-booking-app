package com.example.booking

import com.example.booking.data.Hotel
import com.example.booking.data.User
import com.example.booking.data.UserMin
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface HttpApiService {
   @GET("hotels")
   suspend fun getHotels(): List<Hotel>


   @POST("login")
   suspend fun login(@Body userMin: UserMin): User

   @POST("register")
   suspend fun register(@Body userMin: UserMin)
}