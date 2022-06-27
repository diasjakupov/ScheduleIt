package com.example.scheduleit.data.repository

import com.example.scheduleit.data.datasource.LocalDataSource
import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.days


class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val calendar: Calendar
) : NoteRepository {

    init {
        calendar.time = Date()
    }

    override fun getAllNotesByDay(year:Int, month: Int, day:Int): Flow<List<Note>> {
        //start
        calendar.set(year, month, day, 0, 0 ,0)
        val start = calendar.timeInMillis

        //end
        calendar.set(year, month, day, 23, 59 ,59)
        val end = calendar.timeInMillis
        return localDataSource.getAllNotesByDay(start, end)
    }

    override suspend fun insertNewNote(
        title: String,
        description: String,
        datetime: Long,
        notificationDelay: Int
    ) {
        localDataSource.insertNewNote(title, description, datetime, notificationDelay)
    }

    override suspend fun getTaskByIdAsync(id: Int): Note {
        return localDataSource.getTaskByIdAsync(id)
    }
}