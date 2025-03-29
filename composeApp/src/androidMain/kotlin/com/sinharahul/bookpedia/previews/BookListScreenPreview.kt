package com.sinharahul.bookpedia.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sinharahul.bookpedia.book.domain.Book
import com.sinharahul.bookpedia.book.presentation.book_list.BookListScreenContent
import com.sinharahul.bookpedia.book.presentation.book_list.BookListState

@Preview
@Composable
fun BookListScreenPreview() {
    BookListScreenContent(
        state = BookListState(searchResults = books),
        onAction = {}
    )
}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://example.com/book$it.jpg",
        authors = listOf("Rahul Sinha"),
        description = "Description of Book $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.6578,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}
