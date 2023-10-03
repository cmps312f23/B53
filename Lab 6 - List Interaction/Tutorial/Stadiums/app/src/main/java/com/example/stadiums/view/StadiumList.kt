package com.example.stadiums.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.stadiums.model.Stadium
import com.example.stadiums.ui.theme.StadiumsTheme

@Composable
fun StadiumCard(stadium : Stadium) {

}

@Composable
fun StadiumList() {

}
@Preview(showBackground = true)
@Composable
fun StadiumCardPreview() {
    val stadium = Stadium(
        name = "Alwakra",
        city = "Doha",
        seatingCapacity = 50000,
        status =  "Built and Used",
        imageName = "al_wakra"
    )
    StadiumsTheme {
        StadiumCard(stadium)
    }
}
@Preview(showBackground = true)
@Composable
fun StadiumListPreview() {

}