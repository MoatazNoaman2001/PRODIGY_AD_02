package com.example.prodigytodolist.presentation.ui.todo_list_component

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prodigytodolist.commons.makeToast
import com.example.prodigytodolist.domain.model.Priority
import com.example.prodigytodolist.domain.model.Task
import com.example.prodigytodolist.presentation.AppViewModel
import java.text.SimpleDateFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(navController: NavController, viewModel: AppViewModel) {
    var tasks = viewModel.getAllTasks.collectAsState(initial = emptyList<Task>())
    var isSelectMode = remember { mutableStateOf(false) }
    var selectedItems = remember {
        mutableStateListOf<Task>()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("task_add")
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        topBar = {
            if (isSelectMode.value) {
                TopAppBar(title = { Text(text = "${selectedItems.size} selected") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                isSelectMode.value = false
                                selectedItems.clear()
                            }) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                        }
                    }, actions = {
                        IconButton(onClick = {
                            viewModel.deleteTasks(selectedItems)
                            isSelectMode.value = false
                        }) {
                            Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
                        }

                        IconButton(onClick = {
                            viewModel.deleteTasks(selectedItems)
                            isSelectMode.value = false
                        }) {
                            Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                        }
                    })
            }
        }
    ) {
        val lazyStat = rememberLazyListState()
        Column {


            LazyColumn(modifier = Modifier.padding(it), state = lazyStat) {
                items(items = tasks.value, key = { it.id }) { task ->
                    var isItemSelected = remember { mutableStateOf(false) }
                    TaskLazyItem(
                        task = task,
                        {
                            isSelectMode.value = true
                        },
                        {
                            if (!isSelectMode.value)
                                navController.navigate("task_update/${task.id.toString()}")
                        },
                        isSelectMode.value,
                        isItemSelected.value,
                        {
                            isItemSelected.value = it
                            if (it)
                                selectedItems.add(task)
                            else
                                selectedItems.remove(task)
                        })
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskLazyItem(
    task: Task,
    longClick: () -> Unit,
    onClick: () -> Unit,
    isSelectMode: Boolean,
    isItemSelected: Boolean,
    isItemSelectedState: (Boolean) -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(65.dp)
            .combinedClickable(onLongClick = longClick, onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row {
            if (isSelectMode) {

                Checkbox(checked = isItemSelected, onCheckedChange = isItemSelectedState)
            }
            Column {
                Text(text = task.label, style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = SimpleDateFormat("dd/MM hh:mm a").format(task.Date),
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }

        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = task.priority, fontStyle = FontStyle.Italic, color =
                if (task.priority == Priority.HIGH.name) Color.Red else if (task.priority == Priority.MEDIUM.name) Color.Green else Color.Gray
            )

        }
    }
}