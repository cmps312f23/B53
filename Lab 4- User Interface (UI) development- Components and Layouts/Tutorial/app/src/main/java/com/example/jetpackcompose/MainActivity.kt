package com.example.jetpackcompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val names = listOf(
        "Juan",
        "Pedro",
        "Maria",
        "Juan",
        "Pedro",
        "Maria",
        "Juan",
        "Pedro",
        "Maria",
        "Juan",
        "Pedro",
        "Maria",
        "Juan",
        "Pedro",
        "Maria",
        "Juan",
        "Aya",
        "Salma"
    )
    val showOnBoarding by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary,
    ) {
        if (showOnBoarding)
            OnBoardingScreen(modifier)
        else
            LazyColumn {
                items(names) {
                    Greeting(it)
                }
            }

    }
}

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {
    Column {
        Text("Welcome to Jetpack Compose")
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        Row {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = "Hello $name!",
                    modifier = Modifier
                        .padding(10.dp)

                )
                if (expanded)
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                                "Randome text to be shown only when the show more is clicked"
                    )
            }

            ElevatedButton(onClick = {
                expanded = !expanded
                Log.d("Expanded", "Expanded: " + expanded)

            }) {
                Text(text = "Show More")
            }


        }
    }


}

//@Preview(
//    showBackground = true, uiMode = UI_MODE_NIGHT_YES,
//    name = "Light Mode",
//    showSystemUi = true
//)
@Composable
fun GreetingPreview() {
    JetPackComposeTheme {
        Greeting("Android World")
    }
}

@Preview(
    showBackground = true, uiMode = UI_MODE_NIGHT_YES,
    name = "Light Mode",
    showSystemUi = true
)
@Composable
fun MyAppPreview() {
    JetPackComposeTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

