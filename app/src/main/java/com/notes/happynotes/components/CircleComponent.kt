package com.notes.happynotes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.notes.happynotes.Utils

@Composable
fun CircleComponent(backButtonPadding: Dp = 0.dp, onClick: () -> Unit, color: Color = Utils.offWhite, icon: @Composable () -> Unit) {
    Surface(modifier = Modifier.size(40.dp), shape = CircleShape, color = color) {
        Box(modifier = Modifier.fillMaxSize().clickable { onClick.invoke() }.padding(end = backButtonPadding), contentAlignment = Alignment.Center) {
            icon.invoke()
        }
    }
}