package com.example.scheduleit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note")
data class Note(
    val title:String,
    val description: String,
    val datetime: Long,
    val notificationDelay: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)