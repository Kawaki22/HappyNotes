package com.notes.happynotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class MNote(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val noteBody: String,
    val dateTime: String = SimpleDateFormat("E MMM yyyy hh:mm a", Locale.getDefault()).format(Date()).toString(),
    val color: Long,
    val height: Int
)
