package com.sinharahul.bookpedia.book.data.repository

import com.sinharahul.bookpedia.book.data.mappers.toBook
import com.sinharahul.bookpedia.book.data.network.RemoteBookDataSource
import com.sinharahul.bookpedia.book.domain.Book
import com.sinharahul.bookpedia.book.domain.BookRepository
import com.sinharahul.bookpedia.core.domain.DataError
import com.sinharahul.bookpedia.core.domain.Result
import com.sinharahul.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query).map {
            it.results.map { book ->
                book.toBook()
            }
        }
    }
}
