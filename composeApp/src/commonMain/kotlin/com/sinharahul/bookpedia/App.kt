package com.sinharahul.bookpedia

import androidx.compose.runtime.Composable
import com.sinharahul.bookpedia.book.presentation.book_list.BookListScreen
import com.sinharahul.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<BookListViewModel>()
    BookListScreen(
        viewModel = viewModel,
        onBookClick = {}
    )
}
