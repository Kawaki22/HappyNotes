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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.notes.happynotes.components.HappyNotesAppBar
import com.notes.happynotes.components.NotesCard
import com.notes.happynotes.model.MNote
import com.notes.happynotes.navigation.NavScreen
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel = hiltViewModel()) {

    val isChecked = remember { mutableStateOf(false) }
    val searchState = remember { mutableStateOf("") }

    val notesList = viewModel.noteList.collectAsState()
//    Log.d("NOTESS", "HomeScreen: ${Date.from(Instant.now())}")

    val noNotesColor = if (isChecked.value) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f)

    val notesDummyList = listOf<MNote>(
        MNote(
            title = "Game",
            noteBody = "Staggered Grid View has been seen in most applications such as Pinterest in which each item of grid view takes its own height and aligns within the grid view according to that. In this article, we will look at how to implement",
            color = 0xFF4E33FF,
            height = 200
        )
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { HappyNotesAppBar(title = "Notes", isChecked = isChecked, searchState = searchState) { /*TODO */ } },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier
                .padding(end = 20.dp, bottom = 20.dp),
                shape = CircleShape,
                containerColor = Color(0xFF03A9F4).copy(alpha = 0.8f),
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(2.dp),
                onClick = { navController.navigate(NavScreen.AddNoteScreen.name) }) {
                Icon(modifier = Modifier.size(30.dp), imageVector = Icons.Rounded.Add, contentDescription = "Add Note", tint = Color.White)
            }
    }) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(15.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            //List of Notes Card
            NotesCard(mNoteList = notesList.value, navController = navController, viewModel = viewModel)

            //No notes! Text
            //Only show if notesList is Empty
            if (notesList.value.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    Text(text = "No notes!", style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, color = noNotesColor))
                }
            }
        }

    }
}