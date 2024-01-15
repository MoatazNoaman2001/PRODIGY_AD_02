package com.example.prodigytodolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

enum class Priority{HIGH, MEDIUM, LOW}
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var label:String,
    var Description:String,
    val Date:Date,
    var priority: String = Priority.MEDIUM.name
)
