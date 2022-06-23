package com.example.scheduleit.data.di

import android.content.Context
import androidx.room.Room
import com.example.scheduleit.data.dao.NoteDao
import com.example.scheduleit.data.datasource.LocalDataSource
import com.example.scheduleit.data.datasource.LocalDataSourceImpl
import com.example.scheduleit.data.db.RoomDatabase
import com.example.scheduleit.data.repository.NoteRepository
import com.example.scheduleit.data.repository.NoteRepositoryImpl
import dagger.Binds
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


    @Provides
    @Singleton
    fun provideLocalDataSource(noteDao: NoteDao): LocalDataSource{
        return LocalDataSourceImpl(noteDao = noteDao)
    }


    @Provides
    @Singleton
    fun provideNoteRepository(localDataSource: LocalDataSource): NoteRepository{
        return NoteRepositoryImpl(localDataSource = localDataSource)
    }
}