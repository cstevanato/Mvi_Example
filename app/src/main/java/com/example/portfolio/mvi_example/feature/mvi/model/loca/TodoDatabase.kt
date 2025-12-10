package com.example.portfolio.mvi_example.feature.mvi.model.loca

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database([Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    companion object {
        fun getInstance(context: Context): TodoDatabase {
            return Room.databaseBuilder(context, TodoDatabase::class.java, "todo-db").build()
        }
    }

    abstract fun todoDao(): TodoDao

}