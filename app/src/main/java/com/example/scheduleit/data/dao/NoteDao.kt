package com.example.scheduleit.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE datetime BETWEEN :datetimeSt and :datetimeEnd")
    fun getAllNotesByDay(datetimeSt:Long, datetimeEnd:Long): Flow<List<Note>>

    @Query("""
        INSERT INTO note(title, description, datetime, notificationDelay) 
        VALUES (:title, :description, :time, :notificationDelay)
    """)
    suspend fun insertNewNote(title: String, description: String, time: Long, notificationDelay: Int)
}