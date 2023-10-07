package com.example.stadiums.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stadiums.model.Stadium
import com.example.stadiums.repo.SortOptions
import com.example.stadiums.repo.StadiumRepo

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    filterBy: (String) -> Unit,
    sortBy: (String) -> Unit,
) {
    var sortOptions = listOf(
        SortOptions.CITY,
        SortOptions.CAPACITY,
        SortOptions.NAME
    )

    var query by remember {
        mutableStateOf("")
    }
    var showMenu by remember {
        mutableStateOf(false)
    }
    var sortedBy by remember {
        mutableStateOf(SortOptions.NAME)
    }
    Row {
        TextField(
            value = query,
            onValueChange = {
                query = it
                filterBy(query)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (query.isNotEmpty())
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = modifier.clickable {
                            query = ""
                        }
                    )
            },
            modifier = Modifier.weight(1f)
        )
        Box(modifier = Modifier.width(150.dp)) {
            TextField(
                value = sortedBy,
                onValueChange = { sortedBy = it },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Sort",
                        modifier = modifier
                            .clickable {
                                showMenu = true
                            }
                    )
                },
                readOnly = true
            )
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                sortOptions.forEach {
                    DropdownMenuItem(text = { Text(text = it) }, onClick = {
                        showMenu = false
                        sortedBy = it
                        sortBy(it)
                    })
                }
            }
        }
    }

}