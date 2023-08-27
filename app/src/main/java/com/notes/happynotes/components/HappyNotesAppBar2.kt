package com.notes.happynotes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notes.happynotes.R

@Composable
fun HappyNotesAppBar2(done: () -> Unit, navController: NavController) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            CircleComponent {
                Icon(modifier = Modifier
                    .clip(CircleShape)
                    .size(30.dp)
                    .clickable { navController.popBackStack() }, painter = painterResource(id = R.drawable.back), contentDescription = "Back", tint = Color(0xFF03A9F4))
            }

//            Box {
//
//                Row {
//
//                    Icon(modifier = Modifier
//                        .clip(CircleShape)
//                        .padding(end = 10.dp)
//                        .size(30.dp)
//                        .clickable { done.invoke() }, painter = painterResource(id = R.drawable.delete), contentDescription = "Save Note", tint = Color.Red.copy(alpha = 0.6f))

                    Icon(modifier = Modifier
                        .clip(CircleShape)
                        .size(30.dp)
                        .clickable { done.invoke() }, painter = painterResource(id = R.drawable.done), contentDescription = "Save Note", tint = Color.Green)
//                }
//            }
        }
    }
}