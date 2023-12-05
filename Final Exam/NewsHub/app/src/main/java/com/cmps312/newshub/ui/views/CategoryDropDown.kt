package com.cmps312.newshub.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cmps312.newshub.entity.Category
import com.cmps312.newshub.repository.NewsHubRepo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropDown(selectedCategory: Category, onSelectedOptionChange: (Category) -> Unit) {
    var sortByOptions = NewsHubRepo.getCategories(LocalContext.current)

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf(selectedCategory.category)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
    ) {

        TextField(
            value = selectedOption,
            onValueChange = {
                selectedOption = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Sort By",
                    modifier = Modifier
                        .padding(10.dp)

                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),


            )


        ExposedDropdownMenu(expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {

            sortByOptions.forEach {
                DropdownMenuItem(text = { Text(text = it.category) },
                    onClick = {
                        isExpanded = false
                        selectedOption = it.category
                        onSelectedOptionChange(it)
                    }
                )
            }

        }
    }
}

