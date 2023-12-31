package com.example.stadiums.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stadiums.R
import com.example.stadiums.model.Stadium
import com.example.stadiums.repo.StadiumRepo
import com.example.stadiums.ui.theme.StadiumsTheme

@Composable
fun StadiumCard(stadium: Stadium, modifier: Modifier = Modifier) {
    val imageId = LocalContext.current.resources
        .getIdentifier(
            stadium.imageName,
            "drawable",
            LocalContext.current.packageName
        )

    Card(modifier = modifier.padding(10.dp) .fillMaxWidth()

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = modifier
                    .size(200.dp)
                    .padding(10.dp)

            )
            Column {
                Text(
                    text = "${stadium.name} Stadium",
                    style = MaterialTheme.typography.titleLarge,

                    modifier = modifier.padding(bottom = 10.dp)

                )
                Text(text = "City : ${stadium.city}")
                Text(text = "Capacity :${stadium.seatingCapacity}")
                Text(text = "Status :${stadium.status}")
            }
        }
    }
}

@Composable
fun StadiumList(
    stadiums: List<Stadium>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(stadiums) {
            StadiumCard(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StadiumCardPreview() {
    val stadium = Stadium(
        name = "Alwakra",
        city = "Doha",
        seatingCapacity = 50000,
        status = "Built and Used",
        imageName = "al_wakra"
    )
    StadiumsTheme {
        StadiumCard(stadium)
    }
}

@Preview(showBackground = true)
@Composable
fun StadiumListPreview() {
    var stadiums = StadiumRepo.getStadiums(LocalContext.current)
    StadiumsTheme {
        StadiumList(stadiums)
    }
}