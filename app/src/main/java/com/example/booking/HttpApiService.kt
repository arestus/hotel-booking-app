package com.example.booking


import com.example.booking.data.*
import retrofit2.Call

import retrofit2.http.*

interface HttpApiService {

   @GET("hotels")
   suspend fun getHotels(): List<Hotel>

   @POST("login")
   suspend fun login(@Body userMin: UserMin): User

   @POST("register")
   suspend fun register(@Body userMin: UserMin)

   @DELETE("users/me")
   suspend fun delete()

   @DELETE("users/me/reservations/{reservationId}")
   suspend fun deleteReservation(@Path("reservationId") id: Int)

   @GET("users/me/reservations")
   suspend fun getReservations(): List<Reservation>

   @POST("users/me/reservations")
   suspend fun createReservation(@Body reserv: ReservationMin)

   @POST("users/me/email")
   suspend fun changeEmail(@Body email: String)

   @GET("users/me/loginHistory")
   suspend fun getLoginHistory(): Call<LoginHistory>

   @GET("users")
   suspend fun getLocalUsers(): Call<LocalUser>

}