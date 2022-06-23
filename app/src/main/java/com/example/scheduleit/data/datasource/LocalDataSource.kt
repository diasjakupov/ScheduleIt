package com.example.scheduleit.data.datasource

import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllNotes(): Flow<List<Note>>

    suspend fun insertNewNote(title: String, description: String, datetime: Long, notificationDelay: Int)
}