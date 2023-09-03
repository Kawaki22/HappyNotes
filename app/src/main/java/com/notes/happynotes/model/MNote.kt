package com.notes.happynotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class MNote(
    @PrimaryKey
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val noteBody: String,
    val dateTime: String = SimpleDateFormat("E MMM yyyy hh:mm a", Locale.getDefault()).format(Date()).toString(),
    val color: Long,
    val height: Int
)
