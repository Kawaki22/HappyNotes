package com.notes.happynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.notes.happynotes.navigation.HappyNotesNavigation
import com.notes.happynotes.ui.theme.HappyNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isChecked = remember { mutableStateOf(false) }
            HappyNotesTheme(darkTheme = isChecked.value) {
//                WindowCompat.setDecorFitsSystemWindows(window, false)
                HappyNotesApp(isChecked = isChecked)
            }
        }
    }
}

@Composable
fun HappyNotesApp(isChecked: MutableState<Boolean>) {
    HappyNotesNavigation(isChecked = isChecked)
}