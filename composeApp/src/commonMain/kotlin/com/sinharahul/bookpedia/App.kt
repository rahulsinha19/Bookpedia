package com.sinharahul.bookpedia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sinharahul.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.sinharahul.bookpedia.book.data.repository.DefaultBookRepository
import com.sinharahul.bookpedia.book.presentation.book_list.BookListScreen
import com.sinharahul.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

// 2:39:40
@Composable
@Preview
fun App() {
    BookListScreen(
        viewModel = remember { BookListViewModel(
            bookRepository = DefaultBookRepository(
                remoteBookDataSource = KtorRemoteBookDataSource
            )
        ) },
        onBookClick = {}
    )
}