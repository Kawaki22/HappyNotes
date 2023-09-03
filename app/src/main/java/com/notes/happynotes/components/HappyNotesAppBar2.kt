package com.notes.happynotes.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notes.happynotes.R

@Composable
fun HappyNotesAppBar2(done: () -> Unit, onDelete: () -> Unit = {  }, showDelete: Boolean = false, showDone: Boolean = true, isChecked: Boolean?, navController: NavController) {

    val scaffoldColor = if (isChecked == true) Color.Black else Color.White

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp),
        color = scaffoldColor) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            CircleComponent(color = Color(0xFF03A9F4), backButtonPadding = 2.dp, onClick = { navController.popBackStack() }) {
                Icon(modifier = Modifier
                    .size(20.dp), painter = painterResource(id = R.drawable.back), contentDescription = "Back", tint = Color.Black)
            }

            Box(modifier = Modifier.fillMaxWidth(0.30f)) {
                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                    if (showDelete) {
                        //Delete Icon
                        CircleComponent(color = Color(0xFFF1685D), onClick = { onDelete.invoke() }) {
                            Icon(modifier = Modifier
                                .size(22.dp), painter = painterResource(id = R.drawable.delete), contentDescription = "Delete Note", tint = Color.Black)
                        }
                    } else {
                        Box(modifier = Modifier.width(20.dp))
                    }
                    //Update Icon
                    if (showDone) {
                        CircleComponent(color = Color(0xFFCDDC39), onClick = { done.invoke() }) {
                            Icon(modifier = Modifier
                                .size(20.dp), painter = painterResource(id = R.drawable.done), contentDescription = "Update Note", tint = Color.Black)
                        }
                    }
                }
            }

        }
    }
}