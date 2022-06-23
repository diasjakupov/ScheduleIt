package com.example.scheduleit.data.repository

import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun insertNewNote(title: String, description: String, datetime: Long, notificationDelay: Int)
}