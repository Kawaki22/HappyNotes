package com.notes.happynotes.screens.addnote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.notes.happynotes.components.HappyNotesAppBar2
import com.notes.happynotes.model.MNote
import com.notes.happynotes.screens.home.HomeScreenViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    isChecked: Boolean?
) {

    val title = remember { mutableStateOf("") }
    val body = remember { mutableStateOf("") }

    //for title
    val focusReq = remember { FocusRequester() }
    //for body
    val focusReqBody = remember { FocusRequester() }

    //format: Mon Aug 2023 06:06 AM
    val dateTime = SimpleDateFormat("E MMM yyyy hh:mm a", Locale.getDefault())

    val newNote = MNote(
        title = title.value.trim(),
        noteBody = body.value.trim(),
        color = Random.nextLong(0xFFFFFFFF),
        height = Random.nextInt(220, 300)
    )

    val scaffoldColor = if (isChecked == true) Color.Black else Color.White

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = scaffoldColor,
        topBar = {
            HappyNotesAppBar2(
                done = {
                    if (title.value.trim().isNotEmpty() && body.value.trim().isNotEmpty()) {
                        viewModel.addNote(note = newNote)
                        navController.popBackStack()
                    }
                },
                navController = navController,
                isChecked = isChecked
            )
        }) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 70.dp)
                    .focusRequester(focusReq),
                value = title.value,
                onValueChange = { title.value = it },
                placeholder = {
                    Text(
                        text = "Title",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                keyboardActions = KeyboardActions(onNext = { focusReqBody.requestFocus() }),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(15.dp),
                textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = dateTime.format(Date()),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .focusRequester(focusReqBody),
                value = body.value,
                onValueChange = { body.value = it },
                placeholder = {
                    Text(
                        text = "Start typing.....",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                keyboardActions = KeyboardActions(onNext = { }),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Default
                ),
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