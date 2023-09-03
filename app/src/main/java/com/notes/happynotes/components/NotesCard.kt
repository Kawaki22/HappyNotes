package com.notes.happynotes.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.notes.happynotes.model.MNote
import com.notes.happynotes.navigation.NavScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesCard(mNoteList: List<MNote>, navController: NavController) {
    
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
        items(items = mNoteList) { mNote ->

            NotesCardItem(mNote = mNote, navController = navController)
        }
        item(span = StaggeredGridItemSpan.FullLine) {
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

@Composable
fun NotesCardItem(mNote: MNote, navController: NavController) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth(0.50f)
        .height(mNote.height.dp)
        .clip(RoundedCornerShape(15.dp))
        .clickable { navController.navigate(NavScreen.EditNoteScreen.name + "/${mNote.id}/${mNote.title}/${mNote.noteBody}/${mNote.color}/${mNote.height}") },
        shape = RoundedCornerShape(15.dp),
        color = Color(mNote.color).copy(alpha = 0.8f)
    ) {
       Column(modifier = Modifier
           .fillMaxSize()
           .padding(15.dp),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally) {

           Row(modifier = Modifier.fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.SpaceBetween) {

               Text(modifier = Modifier
                   .fillMaxWidth()
                   .padding(bottom = 8.dp), text = mNote.title, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start, color = Color.White), maxLines = 1, overflow = TextOverflow.Ellipsis)

                }

           Text(text = mNote.noteBody, modifier = Modifier
               .fillMaxWidth()
//               .padding(bottom = 5.dp)
               .height(mNote.height.dp - 90.dp), style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start, color = Color.White), overflow = TextOverflow.Ellipsis)

           Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
               Row(modifier = Modifier.fillMaxWidth(),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween) {
                   Text(text = mNote.dateTime, modifier = Modifier.fillMaxWidth(0.80f), style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start, color = Color.White))
//                   Icon(modifier = Modifier.size(30.dp).clip(CircleShape).clickable { viewModel.deleteNote(id = mNote.id) }, painter = painterResource(id = R.drawable.delete), contentDescription = "Delete Note", tint = Color.White)

               }
           }

       }
    }
}