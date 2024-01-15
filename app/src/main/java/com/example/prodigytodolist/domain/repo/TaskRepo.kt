package com.example.prodigytodolist.domain.repo

import com.example.prodigytodolist.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepo {

    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteAllTasks(tasks: List<Task>)
    suspend fun deleteAllTask()
    fun getAllTasks():Flow<List<Task>>
}