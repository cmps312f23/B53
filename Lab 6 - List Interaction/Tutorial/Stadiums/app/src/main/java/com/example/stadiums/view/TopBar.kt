package com.example.stadiums.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    var query by remember {
        mutableStateOf("")
    }
    Row {
        TextField(
            value = query,
            onValueChange = { query = it },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search")
            },
            trailingIcon = {
                if (query.isNotEmpty())
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "Close")
            }
        )
    }
}