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
fun CartScreen(modifier: Modifier = Modifier) {
    Box(
        Modifier.background(MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Cart Screen",
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 25.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CartScreenPreview() {
    NavBasicsTheme {
        CartScreen()
    }
}