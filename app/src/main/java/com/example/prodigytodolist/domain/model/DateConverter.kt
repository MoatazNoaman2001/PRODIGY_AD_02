package com.example.prodigytodolist.domain.model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.Date


@ProvidedTypeConverter
class DateConverter {

    @TypeConverter
    fun toLong(date: Date) = date.time

    @TypeConverter
    fun toDate(long: Long) = Date(long)
}