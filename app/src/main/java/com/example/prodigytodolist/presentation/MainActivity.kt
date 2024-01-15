package com.example.prodigytodolist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.ProdigyTodoListTheme
import com.example.prodigytodolist.domain.model.Task
import com.example.prodigytodolist.presentation.ui.task_update_component.TaskUpdateScreen
import com.example.prodigytodolist.presentation.ui.todo_task_add_component.TaskDetailsScreen
import com.example.prodigytodolist.presentation.ui.todo_list_component.TodoListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    val viewModel: AppViewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProdigyTodoListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val controllerState = rememberNavController()

                    NavHost(navController = controllerState, startDestination = "list_screen") {
                        composable("list_screen") {
                            TodoListScreen(
                                navController = controllerState,
                                viewModel = viewModel
                            )
                        }
                        composable("task_add"){
                            TaskDetailsScreen(navController = controllerState, viewModel = viewModel)
                        }
                        composable("task_update/{task_id}"){
                            TaskUpdateScreen(it.arguments?.getString("task_id"), controllerState, viewModel)
                        }
                    }


                }
            }
        }
    }
}


