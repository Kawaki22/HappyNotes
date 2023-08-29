package com.notes.happynotes

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.notes.happynotes.navigation.NavScreen
import com.notes.happynotes.screens.home.HomeScreenViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(isChecked: MutableState<Boolean>, navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val scale = remember { androidx.compose.animation.core.Animatable(0f) }
//    val isChecked = if(viewModel.theme.collectAsState().value.isNotEmpty()) viewModel.theme.collectAsState().value.first().isChecked else false
//    val color = if (isChecked) Color.Black else Color.White
    isChecked.value = if (viewModel.theme.collectAsState().value.isNotEmpty()) viewModel.theme.collectAsState().value.first().isChecked else false
    LaunchedEffect(key1 = true) {
        scale.animateTo(0.3f, animationSpec = tween(800))
        delay(1000)
        navController.navigate(NavScreen.HomeScreen.name)
    }

    val scaffoldColor = if (isChecked.value) Color.Black else Color.White

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = scaffoldColor) { innerPadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(modifier = Modifier.scale(scale.value), painter = painterResource(id = R.drawable.happy_notes_logo), contentDescription = "Logo")
        }

    }
}