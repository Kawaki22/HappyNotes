package com.notes.happynotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "theme")
data class MDarkMode(
    @PrimaryKey
    val key: Int = Random.nextInt(),
    //is dark mode option checked
    val isChecked: Boolean
)
