package com.example.bookexplorer.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.repo.BookRepo
import com.example.model.Book
import com.example.bookexplorer.ui.theme.BookExplorerTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookList(
    books: List<Book>,
    contentPaddingValues: PaddingValues,
    onDeleteBook: (Book) -> Unit,
    showBookDetail: (Book) -> Unit
) {
    TODO()
}


@Composable
fun BookCard(book: Book, onDeleteBook: () -> Unit, onShowDetail: (Book) -> Unit) {
    TODO()
}

@Preview(showBackground = true)
@Composable
fun BookListPreview() {
    val books = BookRepo.getBooks(LocalContext.current)
    BookExplorerTheme {
        BookList(
            books = books,
            PaddingValues(10.dp),
            onDeleteBook = {},
            showBookDetail = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    val book = BookRepo.getBooks(LocalContext.current)[0]
    BookExplorerTheme {
        BookCard(
            book = book,
            onDeleteBook = {},
            onShowDetail = {}
        )
    }
}
