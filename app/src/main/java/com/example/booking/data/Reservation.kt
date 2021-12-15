package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(tableName = "reservations")
data class Reservation(
    val userId: Int = 0,
    val hotelId: Int = 0,
    @PrimaryKey
    val reservationId: Int = 0,
    val nightsCount: Int = 0,
    val dateTimestamp: String = ""
)

