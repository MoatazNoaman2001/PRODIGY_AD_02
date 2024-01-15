package com.example.prodigytodolist.commons

import android.app.Application
import androidx.room.Room
import com.example.prodigytodolist.data.db.AppDB
import com.example.prodigytodolist.domain.model.DateConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationHiltComponent {


    @Provides
    @Singleton
    fun getDB(application: Application): AppDB =
        Room.databaseBuilder(application, AppDB::class.java, "TaskDatabase")
            .addTypeConverter(DateConverter()).build()
}