package com.example.prodigytodolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prodigytodolist.domain.model.Priority
import com.example.prodigytodolist.domain.model.Task
import com.example.prodigytodolist.domain.repo.TaskRepo
import com.example.prodigytodolist.domain.repo.TaskRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(private val repo: TaskRepoImpl) : ViewModel() {


    var getAllTasks = repo.getAllTasks()
    fun insertTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteTask(task)
        }
    }

    fun deleteAllTask() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteAllTask()
        }
    }

    fun deleteTasks(task: List<Task>) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteAllTasks(task)
        }
    }
}