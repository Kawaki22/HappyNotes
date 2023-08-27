package com.notes.happynotes.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.notes.happynotes.model.MDarkMode
import com.notes.happynotes.model.MNote

@Database(entities = [MNote::class, MDarkMode::class], version = 1, exportSchema = false)
abstract class HappyNotesDatabase: RoomDatabase() {
    abstract fun happyNotesDao(): HappyNotesDao
}