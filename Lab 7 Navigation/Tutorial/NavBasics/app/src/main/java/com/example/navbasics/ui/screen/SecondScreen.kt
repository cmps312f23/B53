package com.example.navbasics.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.navbasics.ui.theme.NavBasicsTheme


/*
    1. We need to create a class [sealed] Screen that helps us map , some constant values
     - route , Icon and the title
    2. Navigation Host [Graph] Mapping
        --Route to Composable
        --It can extract the parameters in the Path [Route]
        --localhost:3000/api/students/1 ?query =hello
    3. Scafold [bottomBar -NavigationBar , topBar - TopAppBar ] ,
        content part of the scafold will be NavigationHost created in step 2
    4. navigationController.navigate(PATH)  -PATH = "cart" , "cart/1" "cart/1?name=banana
 */

@Composable
fun SecondScreen(modifier: Modifier = Modifier) {
    Box(
        Modifier.background(MaterialTheme.colorScheme.secondary).fillMaxSize(),
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