package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservations")
data class Reservation(
    @PrimaryKey
    val reservationId: Int,
    val userId: Int,
    val hotelId: Int,
    val nightsCount: Int,
    val dateTimestamp: String
)

