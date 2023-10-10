package com.example.navbasics.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.navbasics.ui.theme.NavBasicsTheme


@Composable
fun SecondScreen(modifier: Modifier = Modifier) {
    Box(
        Modifier.background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Second Screen",
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 25.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SecondScreenPreview() {
    NavBasicsTheme {
        SecondScreen()
    }
}