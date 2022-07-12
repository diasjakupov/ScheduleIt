package com.example.scheduleit.data.repository

import com.example.scheduleit.data.datasource.LocalDataSource
import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.days


class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val calendar: Calendar
) : NoteRepository {


    override fun getAllNotesByDay(year: Int, month: Int, day: Int): Flow<List<Note>> {
        //start
        calendar.set(year, month, day, 0, 0, 0)
        val start = calendar.timeInMillis

        //end
        calendar.set(year, month, day, 23, 59, 59)
        val end = calendar.timeInMillis
        return localDataSource.getAllNotesByDay(start, end)
    }

    override suspend fun insertNewNote(
        title: String,
        description: String,
        datetime: Long,
        notificationDelay: Int, status: Boolean
    ) {
        localDataSource.insertNewNote(title, description, datetime, notificationDelay, status)
    }

    override suspend fun getTaskByIdAsync(id: Int): Note {
        delay(500L)
        return localDataSource.getTaskByIdAsync(id)
    }

    override suspend fun updateTask(
        id: Int,
        title: String,
        description: String,
        datetime: Long,
        notificationDelay: Int,
        status: Boolean
    ) {
        localDataSource.updateTask(
            Note(
                id = id,
                title = title,
                description = description,
                datetime = datetime,
                notificationDelay = notificationDelay,
                status = status
            )
        )
    }

    override suspend fun updateStatus(value: Boolean, id: Int) {
        localDataSource.updateStatus(value, id)
    }

    override suspend fun deleteByID(id: Int) {
        localDataSource.deleteByID(id)
    }
}