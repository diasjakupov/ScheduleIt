package com.example.scheduleit.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scheduleit.data.dao.NoteDao
import com.example.scheduleit.data.models.Note


@Database(entities = [Note::class], version = 1)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun NoteDao(): NoteDao
}