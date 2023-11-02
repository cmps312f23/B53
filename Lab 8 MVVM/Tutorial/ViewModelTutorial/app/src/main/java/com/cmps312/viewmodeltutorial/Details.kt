package com.cmps312.viewmodeltutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Details(viewModel: MyViewModel) {
    val student = viewModel.selectedStudent.value
    Column {
        Text(text = "Name : ${student.name} ")
        Text(text = "Age :  ${student.age}")
    }
}