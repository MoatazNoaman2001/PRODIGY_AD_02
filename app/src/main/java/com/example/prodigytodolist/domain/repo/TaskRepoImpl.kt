package com.example.prodigytodolist.domain.repo

import com.example.prodigytodolist.data.db.AppDB
import com.example.prodigytodolist.domain.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TaskRepoImpl @Inject constructor(val appDB: AppDB): TaskRepo{
    override suspend fun insertTask(task: Task) {
        appDB.getTaskDao().insertTask(task)
    }

    override suspend fun updateTask(task: Task) {
        appDB.getTaskDao().updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        appDB.getTaskDao().deleteTask(task)
    }

    override suspend fun deleteAllTasks(tasks: List<Task>) {
        appDB.getTaskDao().deleteAllTasks(tasks)
    }

    override suspend fun deleteAllTask() {
        appDB.getTaskDao().deleteAllTasks()
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return appDB.getTaskDao().getAllTasks()
    }
}