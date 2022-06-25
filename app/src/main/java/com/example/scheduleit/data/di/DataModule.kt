package com.example.scheduleit.data.di

import androidx.room.Insert
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*


@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideCalendar(): Calendar{
        return Calendar.getInstance(TimeZone.getDefault())
    }
}