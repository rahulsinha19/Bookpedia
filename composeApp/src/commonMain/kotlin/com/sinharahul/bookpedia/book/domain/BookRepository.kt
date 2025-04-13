package com.sinharahul.bookpedia.book.domain

import com.sinharahul.bookpedia.core.domain.DataError
import com.sinharahul.bookpedia.core.domain.EmptyResult
import com.sinharahul.bookpedia.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(bookId: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFavoriteBook(bookId: String)
}
