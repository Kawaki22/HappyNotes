package com.notes.happynotes.di

import android.content.Context
import androidx.room.Room
import com.notes.happynotes.room.HappyNotesDao
import com.notes.happynotes.room.HappyNotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHappyNotesDao(notesDatabase: HappyNotesDatabase): HappyNotesDao = notesDatabase.happyNotesDao()

    @Singleton
    @Provides
    fun provideHappyNoteDatabase(@ApplicationContext context: Context): HappyNotesDatabase {
        return Room.databaseBuilder(context, HappyNotesDatabase::class.java, "happy_notes_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}