package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotels")
data class Hotel(
    @PrimaryKey
    val hotelId: Int = 0,
    val url: String = "",
    val roomLeft: Int = 0,
    val pricePerNight: Int = 0,
    val reservedByPeople: Int = 0
)

