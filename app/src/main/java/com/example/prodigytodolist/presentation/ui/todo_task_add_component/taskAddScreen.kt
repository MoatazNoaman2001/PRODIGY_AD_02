package com.example.prodigytodolist.presentation.ui.todo_task_add_component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prodigytodolist.domain.model.Priority
import com.example.prodigytodolist.domain.model.Task
import com.example.prodigytodolist.presentation.AppViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun TaskDetailsScreen(task: Task? = null, navController: NavController?= null, viewModel: AppViewModel?= null) {
    var title = remember { mutableStateOf("") }
    var description = remember { mutableStateOf("") }
    val options = Priority.entries.map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp , 12.dp)
    ) {

        Column(modifier = Modifier
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(14.dp)
            )
            .fillMaxWidth()
            .padding(20.dp)) {
            Text(text = "Task Details" , style = MaterialTheme.typography.displaySmall, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(40.dp))
            TextField(value = title.value, onValueChange = { title.value = it }, label = { Text(text = "title")}, modifier = Modifier.fillMaxWidth())
            TextField(value = description.value, onValueChange = { description.value = it }, label = { Text(text = "description")}, modifier = Modifier.fillMaxWidth())
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }, modifier = Modifier.fillMaxWidth()) {
                TextField(
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = { },
                    modifier = Modifier.fillMaxWidth().menuAnchor(),
                    label = {
                        Text("Priority")
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(text = { Text(text = selectionOption) }, onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        })
                    }
                }
            }
        }

        TextButton(onClick = {
            viewModel?.insertTask(Task(
                0,
                title.value,
                description.value,
                Calendar.getInstance().time,
                selectedOptionText,
            ))

            navController?.popBackStack()

        }) {
            Text(text = "Add Task")
        }
    }
}