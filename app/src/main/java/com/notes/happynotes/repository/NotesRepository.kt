package com.notes.happynotes.repository

import com.notes.happynotes.model.MDarkMode
import com.notes.happynotes.model.MNote
import com.notes.happynotes.room.HappyNotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class NotesRepository @Inject constructor(private val happyNotesDao: HappyNotesDao) {

    fun getAllNotes(): Flow<List<MNote>> = happyNotesDao.getAllNotes().flowOn(Dispatchers.IO)

    suspend fun getNote(noteName: String): MNote = happyNotesDao.getNote(name = noteName)

    suspend fun addNote(note: MNote) = happyNotesDao.addNote(note = note)

    suspend fun editNote(note: MNote) = happyNotesDao.editNote(note = note)

    suspend fun deleteAllNotes() = happyNotesDao.deleteAllNotes()

    suspend fun deleteNote(id: Long) = happyNotesDao.deleteNote(id = id)


    //Dark Mode/Light Mode
    fun getTheme(): Flow<List<MDarkMode>> = happyNotesDao.getTheme().flowOn(Dispatchers.IO)
    suspend fun addTheme(mode: MDarkMode) = happyNotesDao.addTheme(mode = mode)
    suspend fun updateTheme(mode: MDarkMode) = happyNotesDao.updateTheme(mode = mode)

}