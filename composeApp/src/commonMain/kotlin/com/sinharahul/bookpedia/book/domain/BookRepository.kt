package com.sinharahul.bookpedia.book.domain

import com.sinharahul.bookpedia.core.domain.DataError
import com.sinharahul.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}
