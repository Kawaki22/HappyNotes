package com.notes.happynotes.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.notes.happynotes.model.MDarkMode
import com.notes.happynotes.model.MNote
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface HappyNotesDao {

    @Query("SELECT * from notes_tbl")
    fun getAllNotes(): Flow<List<MNote>>

    @Query("SELECT * from notes_tbl where title =:name")
    suspend fun getNote(name: String): MNote

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: MNote)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editNote(note: MNote)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAllNotes()

    @Query("DELETE from notes_tbl where id =:id")
    suspend fun deleteNote(id: UUID)


    //Dark Mode/Light Mode
    @Query("SELECT * from theme")
    fun getTheme(): Flow<List<MDarkMode>>

    @Insert
    suspend fun addTheme(mode: MDarkMode)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTheme(mode: MDarkMode)

}
