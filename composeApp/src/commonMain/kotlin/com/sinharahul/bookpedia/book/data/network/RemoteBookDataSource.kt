package com.sinharahul.bookpedia.book.data.network

import com.sinharahul.bookpedia.book.data.dto.BookWorkDto
import com.sinharahul.bookpedia.book.data.dto.SearchResponseDto
import com.sinharahul.bookpedia.core.domain.DataError
import com.sinharahul.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}
