package com.example.booking.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(tableName = "localUsers")
data class LocalUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String = "",
    val reservationsAt: String = ""
)
