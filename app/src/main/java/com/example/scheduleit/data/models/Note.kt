package com.example.scheduleit.data.models

import androidx.room.Entity


@Entity(tableName = "note")
data class Note(
    val title:String,
    val desc: String,
    val time: Long,
    val notificationDelay: Int
)