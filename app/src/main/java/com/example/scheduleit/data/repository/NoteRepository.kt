package com.example.scheduleit.data.repository

import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotesByDay(year:Int, month: Int, day:Int): Flow<List<Note>>
    suspend fun insertNewNote(title: String, description: String, datetime: Long, notificationDelay: Int, status: Boolean)
    suspend fun getTaskByIdAsync(id:Int): Note
    suspend fun updateTask(id:Int,
        title:String,
                           description: String,
                           datetime: Long,
                           notificationDelay: Int,
                           status: Boolean)
}