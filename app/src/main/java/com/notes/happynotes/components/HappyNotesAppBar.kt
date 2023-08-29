package com.notes.happynotes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notes.happynotes.R
import com.notes.happynotes.model.MDarkMode
import com.notes.happynotes.screens.home.HomeScreenViewModel

@Composable
fun HappyNotesAppBar(modifier: Modifier = Modifier, title: String, isChecked: MutableState<Boolean>, viewModel: HomeScreenViewModel, searchState: MutableState<String>, onSearch: () -> Unit) {

    val mode = if (isChecked.value) R.drawable.dark_mode else R.drawable.light_mode
    val scaffoldColor = if (isChecked.value) Color.Black else Color.White

    val theme = viewModel.theme.collectAsState().value

    if (theme.isEmpty()) viewModel.addTheme(mode = MDarkMode(key = 1, isChecked = isChecked.value))

    val viewModelIsChecked = viewModel.theme.collectAsState().value.first().isChecked


//    val themeFromRoom = remember { mutableStateOf(if (theme.isNotEmpty()) theme.first().isChecked else isChecked.value) }

    //Setting isChecked from Room to isChecked.value
    //if list is empty use false, list will be empty at the first launch of the app
    LaunchedEffect(key1 = viewModel.theme.collectAsState().value.first().isChecked) {
        isChecked.value = viewModelIsChecked
    }


    Surface(modifier = modifier
        .fillMaxWidth()
        .height(160.dp)
        .padding(top = 20.dp),
        color = scaffoldColor
    ) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Text(text = title, style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold, color = if (isChecked.value) Color.White else Color.Black))

                Switch(checked = isChecked.value, onCheckedChange = {
                    isChecked.value = it
                        viewModel.updateTheme(mode = MDarkMode(key = 1, isChecked = isChecked.value))
                                                                                           },
                    colors = SwitchDefaults.colors(checkedBorderColor = Color.Transparent, checkedTrackColor = Color.White, checkedThumbColor = Color.Black, uncheckedBorderColor = Color.Transparent, uncheckedThumbColor = Color.White, uncheckedTrackColor = Color.Black),
                    thumbContent = { Icon(painter = painterResource(id = mode), contentDescription = "Dark mode", tint = if (isChecked.value) Color.White else Color.Black, modifier = Modifier.size(20.dp)) })

            }

            //SearchBox
            SearchBox(modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth(), searchState = searchState) {
                //OnSearch
                onSearch.invoke()
            }
        }
    }
}