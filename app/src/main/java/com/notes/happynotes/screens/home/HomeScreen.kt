package com.notes.happynotes.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.notes.happynotes.components.BottomNavBar
import com.notes.happynotes.components.HappyNotesAppBar
import com.notes.happynotes.components.NotesCard
import com.notes.happynotes.model.MNote
import com.notes.happynotes.navigation.NavScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(isChecked: MutableState<Boolean>, navController: NavHostController, viewModel: HomeScreenViewModel = hiltViewModel()) {

    val searchState = remember { mutableStateOf("") }

    val notesList = viewModel.noteList.collectAsState()
    var searchNote = emptyList<MNote>()

    val noNotesColor = if (isChecked.value) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f)
    val bottomBarColor = if (isChecked.value) Color(0xFF332E2E) else Color.Black

    searchNote = notesList.value.filter { mNote -> mNote.title.contains(searchState.value.trim()) }

//    val notesDummyList = listOf<MNote>(
//        MNote(
//            title = "Game",
//            noteBody = "Staggered Grid View has been seen in most applications such as Pinterest in which each item of grid view takes its own height and aligns within the grid view according to that. In this article, we will look at how to implement",
//            color = 0xFF4E33FF,
//            height = 200
//        )
//    )

    val scaffoldColor = if (isChecked.value) Color.Black else Color.White

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {  },
        containerColor = scaffoldColor,
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier
                .size(85.dp)
                .padding(end = 20.dp, bottom = 20.dp),
                shape = CircleShape,
                containerColor = bottomBarColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(2.dp),
                onClick = { navController.navigate(NavScreen.AddNoteScreen.name) }) {
                Icon(modifier = Modifier.size(30.dp), imageVector = Icons.Rounded.Add, contentDescription = "Add Note", tint = /*if (isChecked.value) Color.Black else */ Color.White)
            }
    }) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            //AppBar
            HappyNotesAppBar(title = "Notes", isChecked = isChecked, viewModel = viewModel, searchState = searchState) {  }

            //show filtered notes if searched
            if (searchNote.isNotEmpty()) {
                NotesCard(mNoteList = searchNote, navController = navController, viewModel = viewModel)
            } else {
                //List of Notes Card
                //All notes
                NotesCard(mNoteList = notesList.value, navController = navController, viewModel = viewModel)
            }

            //No notes! Text
            //Only show if notesList is Empty
            if (notesList.value.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

                    Text(text = "No notes!", style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, color = noNotesColor), modifier = Modifier.padding(100.dp))
                }
            }
        }
        BottomNavBar(isChecked = isChecked.value, navBarColor = bottomBarColor, navController = navController, viewModel = viewModel)



    }
}