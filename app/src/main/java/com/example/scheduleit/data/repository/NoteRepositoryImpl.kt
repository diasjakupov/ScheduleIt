package com.example.scheduleit.data.repository

import com.example.scheduleit.data.datasource.LocalDataSource
import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return localDataSource.getAllNotes()
    }

    override suspend fun insertNewNote(
        title: String,
        description: String,
        datetime: Long,
        notificationDelay: Int
    ) {
        localDataSource.insertNewNote(title, description, datetime, notificationDelay)
    }
}