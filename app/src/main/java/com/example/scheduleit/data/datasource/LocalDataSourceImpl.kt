package com.example.scheduleit.data.datasource

import com.example.scheduleit.data.dao.NoteDao
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(
    private val noteDao: NoteDao
) : LocalDataSource {
    override fun getAllNotes() = noteDao.getAllNotes()

    override suspend fun insertNewNote(
        title: String,
        description: String,
        datetime: Long,
        notificationDelay: Int
    ) {
        noteDao.insertNewNote(title, description, datetime, notificationDelay)
    }

}