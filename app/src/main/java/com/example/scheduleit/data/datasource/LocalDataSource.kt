package com.example.scheduleit.data.datasource

import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllNotesByDay(start:Long, end:Long): Flow<List<Note>>
    suspend fun getTaskByIdAsync(id:Int): Note
    suspend fun insertNewNote(title: String, description: String, datetime: Long, notificationDelay: Int, status: Boolean)
    suspend fun updateTask(task: Note)
}