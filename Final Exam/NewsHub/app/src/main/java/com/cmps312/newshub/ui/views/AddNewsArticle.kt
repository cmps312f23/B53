package com.cmps312.newshub.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import com.cmps312.newshub.ui.theme.NewsHubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewsArticle(
    onAddNewsArticle: (Article) -> Unit
) {
    var title by remember { mutableStateOf(TextFieldValue()) }
    var article by remember { mutableStateOf(TextFieldValue()) }
    var image by remember { mutableStateOf(TextFieldValue()) }
    var author by remember { mutableStateOf(TextFieldValue()) }
    var date by remember { mutableStateOf(TextFieldValue()) }
    var selectedCategory by remember { mutableStateOf(Category("NONE")) }

    OutlinedCard(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(8.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp).fillMaxHeight(),
        //set card elevation of the card
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                "Add News Info",
                fontSize = 30.sp,
                letterSpacing = 3.sp,
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Title") }
            )

            // Article
            OutlinedTextField(
                value = article,
                onValueChange = { article = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Article") },
                maxLines = 10
            )

            // Image
            OutlinedTextField(
                value = image,
                onValueChange = { image = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Image Name") }
            )

            // Category
            CategoryDropDown(Category(category = "NONE")) {
                selectedCategory = it
            }

            // Author
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Author") }
            )

            // Date
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Date") }
            )
            Button(
                onClick = {
                    val newArticle = Article(
                        title.text,
                        article.text,
                        image.text,
                        selectedCategory.id,
                        author.text,
                        date.text
                    )
                    onAddNewsArticle(newArticle)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text("Add Article")
            }
        }
    }
}

@Preview
@Composable
fun AddNewsArticle() {
    val context = LocalContext.current
    NewsHubTheme {
        AddNewsArticle() {
            Toast.makeText(context, it.author, Toast.LENGTH_SHORT).show()
        }
    }
}
