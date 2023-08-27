package com.notes.happynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.notes.happynotes.navigation.HappyNotesNavigation
import com.notes.happynotes.ui.theme.HappyNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyNotesTheme {
//                WindowCompat.setDecorFitsSystemWindows(window, false)
                HappyNotesApp()
            }
        }
    }
}

@Composable
fun HappyNotesApp() {
    HappyNotesNavigation()
}