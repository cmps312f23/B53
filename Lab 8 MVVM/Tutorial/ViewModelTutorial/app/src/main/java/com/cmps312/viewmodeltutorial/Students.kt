package com.cmps312.viewmodeltutorial

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Student(val name : String , val age : Int)

object StudentRepo{
    val students = listOf(
        Student("Ali", 22),
        Student("Omar", 21),
        Student("Aya", 23),
        Student("Aisha", 20),
        Student("Fatima", 19),
    )
}

@Composable
fun Students(){
    LazyColumn{
        items(StudentRepo.students){
            Text(text = it.name , fontSize = 20.sp , modifier = Modifier.padding(16.dp))
        }
    }
}