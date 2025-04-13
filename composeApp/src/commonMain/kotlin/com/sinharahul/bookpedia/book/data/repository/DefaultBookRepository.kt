package com.sinharahul.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.sinharahul.bookpedia.book.data.database.FavoriteBookDao
import com.sinharahul.bookpedia.book.data.mappers.toBook
import com.sinharahul.bookpedia.book.data.mappers.toBookEntity
import com.sinharahul.bookpedia.book.data.network.RemoteBookDataSource
import com.sinharahul.bookpedia.book.domain.Book
import com.sinharahul.bookpedia.book.domain.BookRepository
import com.sinharahul.bookpedia.core.domain.DataError
import com.sinharahul.bookpedia.core.domain.EmptyResult
import com.sinharahul.bookpedia.core.domain.Result
import com.sinharahul.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query).map {
            it.results.map { book ->
                book.toBook()
            }
        }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError.Remote> {
        val localResult = favoriteBookDao.getFavoriteBookById(bookId)
        return if (localResult == null) {
            remoteBookDataSource.getBookDetails(bookId).map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao.getFavoriteBooks().map { bookEntities ->
            bookEntities.map { it.toBook() }
        }
    }

    override fun isBookFavorite(bookId: String): Flow<Boolean> {
        return favoriteBookDao.getFavoriteBooks().map { bookEntities ->
            bookEntities.any { it.id == bookId }
        }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (ex: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFavoriteBook(bookId: String) {
        favoriteBookDao.deleteFavoriteBook(bookId)
    }
}
