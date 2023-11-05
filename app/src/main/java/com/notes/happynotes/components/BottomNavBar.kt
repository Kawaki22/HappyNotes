package com.notes.happynotes.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.notes.happynotes.R
import com.notes.happynotes.navigation.NavScreen
import com.notes.happynotes.screens.home.HomeScreenViewModel

@Composable
fun BottomNavBar(
    isChecked: Boolean,
    navBarColor: Color,
    navController: NavController,
    viewModel: HomeScreenViewModel
) {

    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val circleColor = if (isChecked) Color.White else Color.White
    val iconColor = if (isChecked) Color.Black else Color.Black

    //NavBar
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 45.dp, bottom = 30.dp), contentAlignment = Alignment.BottomStart
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .height(75.dp), shape = CircleShape,
            color = navBarColor
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BottomNavBarItems(
                    circleColor = circleColor,
                    iconColor = iconColor,
                    onClick = { showDialog.value = !showDialog.value },
                    icon = R.drawable.delete,
                    title = "Delete all notes"
                )
                BottomNavBarItems(
                    circleColor = circleColor,
                    iconColor = iconColor,
                    onClick = { navController.navigate(NavScreen.AboutScreen.name) },
                    icon = R.drawable.about,
                    title = "About"
                )
            }
        }
    }

    //Alert Dialog
    if (showDialog.value) {
        AlertDialog(onDismissRequest = { showDialog.value = false },
            confirmButton = {
                Button(onClick = {
                    viewModel.deleteAllNotes()
                    Toast.makeText(context, "Notes Deleted", Toast.LENGTH_LONG).show()
                    showDialog.value = false
                }, shape = RoundedCornerShape(12.dp)) {
                    Text(
                        text = "Confirm",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = !showDialog.value },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Cancel",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            },
            title = {
                Text(
                    text = "Delete all",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to delete all notes?",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal)
                )
            })
    }
}

@Composable
fun BottomNavBarItems(
    circleColor: Color,
    iconColor: Color,
    onClick: () -> Unit,
    icon: Int,
    title: String
) {
    CircleComponent(onClick = { onClick.invoke() }, color = circleColor) {
        Icon(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = iconColor
        )
    }
}

@Composable
fun MaterialNavBar() {

}