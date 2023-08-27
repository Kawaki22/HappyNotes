package com.notes.happynotes.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notes.happynotes.Utils

@Composable
fun CustomAlertDialog(heading: String, text: String, showDialog: MutableState<Boolean>, onConfirm: () -> Unit) {

//    AnimatedVisibility(visible = showDialog.value, enter = fadeIn(animationSpec = tween(100)), exit = fadeOut(animationSpec = tween(100))) {

        Column(modifier = Modifier.fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Surface(modifier = Modifier
                .fillMaxWidth(0.80f)
                .height(120.dp), shape = RoundedCornerShape(15.dp),
                border = BorderStroke(2.dp, color = Utils.offWhite)
            ) {
                Column(modifier = Modifier.fillMaxSize()
                    .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start) {

                    Text(text = heading, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start))
                    Text(text = text, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start))

                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(onClick = { onConfirm.invoke() }, shape = RoundedCornerShape(12.dp)) {
                            Text(text = "Confirm", style = TextStyle(fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
                        }
                        Button(onClick = { showDialog.value = false }, shape = RoundedCornerShape(12.dp)) {
                            Text(text = "Cancel", style = TextStyle(fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
//    }
}