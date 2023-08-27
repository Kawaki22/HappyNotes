package com.notes.happynotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.notes.happynotes.screens.addnote.AddNoteScreen
import com.notes.happynotes.screens.home.HomeScreen

@Composable
fun HappyNotesNavigation () {
    val navController = rememberNavController()
     NavHost(navController = navController, startDestination = NavScreen.HomeScreen.name) {

         composable(NavScreen.HomeScreen.name) {
             HomeScreen(navController = navController)
         }
         composable(NavScreen.AddNoteScreen.name) {
             AddNoteScreen(navController = navController)
         }
     }
}