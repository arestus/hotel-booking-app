package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(tableName = "hotels")
data class Hotel(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val url: String = "",
    val roomsLeft: Int = 0,
    val pricePerNight: Int = 0,
    val reservedByPeople: Int = 0
)

