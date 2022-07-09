package com.example.scheduleit.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.scheduleit.data.models.Note
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE datetime BETWEEN :datetimeSt and :datetimeEnd")
    fun getAllTasksByDay(datetimeSt:Long, datetimeEnd:Long): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id=:id")
    suspend fun getTaskByIdAsync(id:Int): Note

    @Query("""
        INSERT INTO note(title, description, datetime, notificationDelay, status) 
        VALUES (:title, :description, :time, :notificationDelay, :status)
    """)
    suspend fun insertNewTask(title: String, description: String, time: Long, notificationDelay: Int, status:Boolean)

    @Update()
    suspend fun updateTask(task: Note)
}