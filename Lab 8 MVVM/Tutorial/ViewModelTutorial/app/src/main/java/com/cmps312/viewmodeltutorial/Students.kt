package com.cmps312.viewmodeltutorial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Student(val name: String, val age: Int)

object StudentRepo {
    val students = listOf(
        Student("Ali", 22),
        Student("Omar", 21),
        Student("Aya", 23),
        Student("Aisha", 20),
        Student("Fatima", 19),
    )
}

@Composable
fun Students(viewModel: MyViewModel) {
    val students = viewModel.students.collectAsState().value

    LazyColumn {
        items(students) {
            Text(text = it.name, fontSize = 20.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        viewModel.showStudentDetail(it)
                    })
        }
    }
}