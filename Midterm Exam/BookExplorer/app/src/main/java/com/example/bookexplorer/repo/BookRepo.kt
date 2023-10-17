package com.example.repo

import android.content.Context
import com.example.model.Book
import kotlinx.serialization.json.Json

object BookRepo {
    var books = mutableListOf<Book>()

    fun getBooks(context: Context): List<Book> {
        TODO()
    }

    fun searchBooks(context: Context, search: String): List<Book> = TODO()
    fun deleteBook(book: Book): Unit = TODO()
}