package com.example.prodigytodolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.prodigytodolist.domain.model.DateConverter
import com.example.prodigytodolist.domain.model.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao;
}