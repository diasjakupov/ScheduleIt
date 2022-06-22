package com.example.scheduleit.data.di

import android.content.Context
import androidx.room.Room
import com.example.scheduleit.data.dao.NoteDao
import com.example.scheduleit.data.db.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase{
        return Room.databaseBuilder(context, RoomDatabase::class.java, "note_db").build()
    }


    @Provides
    @Singleton
    fun provideNoteDao(db: RoomDatabase): NoteDao{
        return db.NoteDao()
    }
}