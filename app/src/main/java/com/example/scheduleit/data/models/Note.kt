package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note")
data class Note(
    val title:String,
    val description: String,
    val datetime: Long,
    val notificationDelay: Int,
    val status: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)