package com.example.portfolio.mvi_example.feature.mvi.model.loca

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val isDone: Boolean,
)
