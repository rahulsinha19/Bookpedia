package com.sinharahul.bookpedia.book.presentation.book_list

import com.sinharahul.bookpedia.book.domain.Book
import com.sinharahul.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favouriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
