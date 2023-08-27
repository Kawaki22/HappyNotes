package com.notes.happynotes.screens.editnote

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.ui.unit.sp
import com.notes.happynotes.components.HappyNotesAppBar2
import com.notes.happynotes.model.MNote
import com.notes.happynotes.screens.home.HomeScreenViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import java.util.UUID
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(id: UUID, title: String, body: String, color: Long, height: Int, navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()) {

    val editTitle = remember { mutableStateOf(title) }
    val editBody = remember { mutableStateOf(body) }

    //for title
    val focusReq = remember { FocusRequester() }
    //for body
    val focusReqBody = remember { FocusRequester() }

    //format: Mon Aug 2023 06:06 AM
    val dateTime = SimpleDateFormat("E MMM yyyy hh:mm a", Locale.getDefault())

    val newNote = MNote(
        id = id,
        title = editTitle.value.trim(),
        noteBody = editBody.value.trim(),
        color = color,
        height = height
    )

    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { HappyNotesAppBar2(showDelete = true,
            onDelete = {
                viewModel.deleteNote(id = id)
                Toast.makeText(context, "Note deleted", Toast.LENGTH_LONG).show()
                navController.popBackStack()
                       },
            done = { if (editTitle.value.trim().isNotEmpty() && editBody.value.trim().isNotEmpty() ) {
            viewModel.editNote(note = newNote)
            navController.popBackStack()
        } },
            navController = navController)}) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            TextField(modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 70.dp).focusRequester(focusReq), value = editTitle.value, onValueChange = { editTitle.value = it },
                placeholder = { Text(text = "Title", style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start, color = Color.Black.copy(alpha = 0.5f))) },
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, containerColor = Color.Transparent),
                keyboardActions = KeyboardActions(onNext = { focusReqBody.requestFocus() }),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                shape = RoundedCornerShape(15.dp),
                textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
            )

            Text(modifier = Modifier.fillMaxWidth(), text = dateTime.format(Date()), style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))

            TextField(modifier = Modifier.fillMaxWidth().fillMaxHeight().focusRequester(focusReqBody), value = editBody.value, onValueChange = { editBody.value = it },
                placeholder = { Text(text = "Start typing.....", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start, color = Color.Black.copy(alpha = 0.5f))) },
                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, containerColor = Color.Transparent),
                keyboardActions = KeyboardActions(onNext = {  }),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                singleLine = false,
                shape = RoundedCornerShape(15.dp),
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal)
            )
        }
    }

    LaunchedEffect(key1 = true) {
        focusReq.requestFocus()
    }
}