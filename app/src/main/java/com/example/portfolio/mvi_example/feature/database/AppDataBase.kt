package com.example.portfolio.mvi_example.feature.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.dao.CepDao
import com.example.portfolio.mvi_example.feature.cepMvi.data.local.entiry.AddressEntity
import com.example.portfolio.mvi_example.feature.mvi.model.loca.Todo
import com.example.portfolio.mvi_example.feature.mvi.model.loca.TodoDao

@Database([AddressEntity::class, Todo::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "app-db").build()
        }
    }

    abstract fun todoDao(): TodoDao
    abstract fun cepDao(): CepDao
}