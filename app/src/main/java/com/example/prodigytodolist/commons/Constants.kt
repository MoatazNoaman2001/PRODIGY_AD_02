package com.example.prodigytodolist.commons

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun makeToast(context: Context, text:String) = Toast
    .makeText(context, text, Toast.LENGTH_SHORT)
    .show()