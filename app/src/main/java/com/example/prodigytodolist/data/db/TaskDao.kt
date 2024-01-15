package com.example.prodigytodolist.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.prodigytodolist.domain.model.Task
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteAllTasks(tasks: List<Task>)

    @Query("DELETE FROM task")
    suspend fun deleteAllTasks()


    @Query("select * from task order by Date desc")
    fun getAllTasks(): Flow<List<Task>>
}