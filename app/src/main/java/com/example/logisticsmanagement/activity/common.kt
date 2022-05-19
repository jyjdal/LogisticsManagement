package com.example.logisticsmanagement.activity

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.logisticsmanagement.FormInputKeyboardType

// 用于快速显示Toast和打印日志的扩展方法
fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun String.log(level: Int = Log.INFO, tag: String = "Application log") {
    Log.println(level, tag, this)
}

// 对一些常用的对话框进行封装
val PRIMARY_TEXT_SIZE = 24.sp
val SECONDARY_TEXT_SIZE = 20.sp

@Composable
fun TextDialog(
    showDialog: MutableState<Boolean>,
    title: String,
    message: String,
    confirmText: String? = null
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = title, fontSize = SECONDARY_TEXT_SIZE) },
            text = { Text(text = message, fontSize = PRIMARY_TEXT_SIZE) },
            confirmButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text(text = confirmText ?: "确认", fontSize = SECONDARY_TEXT_SIZE)
                }
            },
            modifier = Modifier.width(320.dp)
        )
    }
}

@Composable
fun CustomContentDialog(
    showDialog: MutableState<Boolean>,
    title: String,
    confirmText: String? = null,
    content: @Composable (() -> Unit),
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = title, fontSize = SECONDARY_TEXT_SIZE) },
            text = { content() },
            confirmButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text(text = confirmText ?: "确认", fontSize = SECONDARY_TEXT_SIZE)
                }
            }
        )
    }
}

@Composable
fun FormInput(
    value: MutableState<String>,
    label: String,
    modifier: Modifier = Modifier,
    required: Boolean = false,
    inputType: FormInputKeyboardType = FormInputKeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        modifier = modifier,
        label = { Text(text = label, fontSize = 20.sp) },
        isError = if (inputType == FormInputKeyboardType.Number)
            !(value.value.isDigitsOnly())
        else if (required)
            value.value.isBlank()
        else false,
        visualTransformation = if (inputType == FormInputKeyboardType.Password)
            PasswordVisualTransformation()
        else VisualTransformation.None,
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (value.value.isNotEmpty()) {
                IconButton(onClick = { value.value = "" }) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = null
                    )
                }
            }
        },
        keyboardOptions = when (inputType) {
            FormInputKeyboardType.Text -> KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
            FormInputKeyboardType.Number -> KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
            FormInputKeyboardType.Password -> KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        },
        keyboardActions = keyboardActions
    )
}
