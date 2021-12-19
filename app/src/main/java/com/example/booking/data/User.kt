package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int = 0,
    val email: String = "",
    var password: String? = null,
    val token: String = "",
    val memberSince: Long = 0
)
