package com.notes.happynotes.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.notes.happynotes.SplashScreen
import com.notes.happynotes.screens.about.AboutScreen
import com.notes.happynotes.screens.addnote.AddNoteScreen
import com.notes.happynotes.screens.editnote.EditNoteScreen
import com.notes.happynotes.screens.home.HomeScreen
import java.util.UUID

@Composable
fun HappyNotesNavigation(isChecked: MutableState<Boolean>) {
    val navController = rememberNavController()
     NavHost(navController = navController, startDestination = NavScreen.SplashScreen.name) {

         composable(NavScreen.SplashScreen.name) {
             SplashScreen(isChecked = isChecked, navController = navController)
         }

         Log.d("DARKK", "HappyNotesNavigation: ${isChecked.value}")

         composable(NavScreen.HomeScreen.name) {
             HomeScreen(isChecked = isChecked, navController = navController)
         }
         composable(NavScreen.AddNoteScreen.name) {
             AddNoteScreen(navController = navController)
         }
         val editScreen = NavScreen.EditNoteScreen.name
         composable("$editScreen/{id}/{title}/{body}/{color}/{height}", arguments = listOf(
             navArgument("id") {
                 type = NavType.StringType
             },
             navArgument("title") {
                 type = NavType.StringType
             },
             navArgument("body" ) {
                 type = NavType.StringType
             },
             navArgument("color") {
                 type = NavType.LongType
             },
             navArgument("height") {
                 type = NavType.IntType
             }
         )) { backStack ->
             val id = backStack.arguments?.getString("id")
             val title = backStack.arguments?.getString("title")
             val body = backStack.arguments?.getString("body")
             val color = backStack.arguments?.getLong("color")
             val height = backStack.arguments?.getInt("height")
             EditNoteScreen(navController = navController, id = UUID.fromString(id), title = title!!, body = body!!, color = color!!, height = height!!)
         }
         composable(NavScreen.AboutScreen.name) {
             AboutScreen(navController = navController)
         }
     }
}