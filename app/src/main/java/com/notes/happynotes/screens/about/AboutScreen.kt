package com.notes.happynotes.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.notes.happynotes.R
import com.notes.happynotes.components.HappyNotesAppBar2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController, isChecked: Boolean?) {

    val scaffoldColor = if (isChecked == true) Color.Black else Color.White
    val fontColor = if (isChecked == true) Color.White else Color.Black

    val uriHandler = LocalUriHandler.current
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {  },
        containerColor = scaffoldColor) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(20.dp)
            .fillMaxSize()
            .verticalScroll(ScrollState(0)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            //AppBar
            HappyNotesAppBar2(showDone = false, done = {  }, navController = navController, isChecked = isChecked)

            Image(painter = painterResource(id = R.drawable.about_pic), contentDescription = "Pic")
            Text(modifier = Modifier.padding(bottom = 20.dp), text = "\"Happy Notes\" is a meticulously crafted Android application that brings the art of note-taking to a new level of convenience and delight. Built with the modern MVVM (Model-View-ViewModel) architecture and powered by the robust Room Database, this app offers an exceptional note-taking experience that seamlessly combines functionality with elegance.", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = fontColor, textAlign = TextAlign.Center))
            Image(modifier = Modifier
                .width(100.dp)
                .height(50.dp)
                .clickable { uriHandler.openUri("https://github.com/Kawaki22/") },
                painter = painterResource(id = R.drawable.github), contentDescription = "GitHub", colorFilter = ColorFilter.tint(fontColor))

            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}