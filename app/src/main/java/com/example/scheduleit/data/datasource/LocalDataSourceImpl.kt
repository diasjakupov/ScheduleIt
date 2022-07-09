package com.example.scheduleit.data.datasource

import com.example.scheduleit.data.dao.NoteDao
import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(
    private val noteDao: NoteDao
) : LocalDataSource {
    override fun getAllNotesByDay(start: Long, end: Long): Flow<List<Note>> = noteDao.getAllTasksByDay(start, end)
    override suspend fun getTaskByIdAsync(id: Int): Note {
        return withContext(Dispatchers.IO){
            noteDao.getTaskByIdAsync(id)
        }
    }

    override suspend fun insertNewNote(
        title: String,
        description: String,
        datetime: Long,
        notificationDelay: Int, status: Boolean
    ) {
        noteDao.insertNewTask(title, description, datetime, notificationDelay, status)
    }

    override suspend fun updateTask(task: Note) {
        noteDao.updateTask(task)
    }

}