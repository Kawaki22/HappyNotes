package com.notes.happynotes.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notes.happynotes.model.MDarkMode
import com.notes.happynotes.model.MNote
import com.notes.happynotes.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val notesRepository: NotesRepository): ViewModel() {

    private val _notesList = MutableStateFlow<List<MNote>>(emptyList())
    val noteList = _notesList.asStateFlow()

    private val _theme = MutableStateFlow<List<MDarkMode>>(emptyList())
    val theme = _theme.asStateFlow()

    init {
        getAllNotes()
        getTheme()
//        Log.d("THEMEE", "HappyNotesAppBar: $theme")
    }

    private fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {

            notesRepository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                if (listOfNotes.isNotEmpty()) {
                    _notesList.value = listOfNotes
                } else {
                    _notesList.value = emptyList()
                }
            }
        }
    }

    /*
    fun getNote(noteName: String): MNote {
        var note: MNote = MNote(title = "", noteBody = "", color = 0xFF000000, height = 100)
        viewModelScope.launch(Dispatchers.IO) {
           note = notesRepository.getNote(noteName = noteName)
        }
        return note
    }
     */

    fun addNote(note: MNote) {
        viewModelScope.launch(Dispatchers.IO) {

            notesRepository.addNote(note = note)

        }
    }

    fun editNote(note: MNote) {
        viewModelScope.launch(Dispatchers.IO) {

            notesRepository.editNote(note = note)

        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {

            notesRepository.deleteAllNotes()

        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {

            notesRepository.deleteNote(id = id)

        }
    }

    private fun getTheme() {
//        var mode: MDarkMode? = null
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getTheme().distinctUntilChanged().collect { listOfMDark ->

                if (listOfMDark.isNotEmpty()) {
                    _theme.value = listOfMDark
                } else {
                    _theme.value = emptyList()
                }
            }
//            Log.d("THEMEE", "HappyNotesAppBar: $mode")
        }
//        return mode
    }

    fun addTheme(mode: MDarkMode) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.addTheme(mode = mode)
        }
    }

    fun updateTheme(mode: MDarkMode) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateTheme(mode = mode)
        }
    }
}