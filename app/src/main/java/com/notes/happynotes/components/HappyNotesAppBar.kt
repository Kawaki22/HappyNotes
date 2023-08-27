package com.notes.happynotes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HappyNotesAppBar(modifier: Modifier = Modifier, title: String, isChecked: MutableState<Boolean>, searchState: MutableState<String>, onSearch: () -> Unit) {

    Surface(modifier = modifier
        .fillMaxWidth()
        .height(160.dp)
        .padding(top = 20.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Text(text = title, style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold))

                Switch(checked = isChecked.value, onCheckedChange = { isChecked.value = it },
                    colors = SwitchDefaults.colors(checkedBorderColor = Color.Transparent, checkedTrackColor = Color.White, checkedThumbColor = Color.Black, uncheckedBorderColor = Color.Transparent, uncheckedThumbColor = Color.White, uncheckedTrackColor = Color.Black),
                )

            }

            //SearchBox
            SearchBox(modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(), searchState = searchState) {
                //OnSearch
                onSearch.invoke()
            }
        }
    }
}