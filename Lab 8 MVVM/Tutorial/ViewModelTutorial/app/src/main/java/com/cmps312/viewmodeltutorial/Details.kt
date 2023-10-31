package com.cmps312.viewmodeltutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Details(viewModel: MyViewModel){
    Column {
        Text(text = "Name :  ")
        Text(text = "Age :  ")
    }
}