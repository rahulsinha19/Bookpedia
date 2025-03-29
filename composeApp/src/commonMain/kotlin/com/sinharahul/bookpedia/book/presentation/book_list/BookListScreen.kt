package com.sinharahul.bookpedia.book.presentation.book_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sinharahul.bookpedia.book.domain.Book
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookListScreen(
    onBookClick: (Book) -> Unit,
    viewModel: BookListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreenContent(
        state = state,
        onAction = { action ->
            when(action) {
                is BookListAction.OnBookClick -> onBookClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun BookListScreenContent(
    state: BookListState,
    onAction: (BookListAction) -> Unit
) {

}
