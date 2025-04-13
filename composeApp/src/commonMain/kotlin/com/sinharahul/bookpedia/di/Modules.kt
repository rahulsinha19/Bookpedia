package com.sinharahul.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.sinharahul.bookpedia.book.data.database.DatabaseFactory
import com.sinharahul.bookpedia.book.data.database.FavoriteBookDatabase
import com.sinharahul.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.sinharahul.bookpedia.book.data.network.RemoteBookDataSource
import com.sinharahul.bookpedia.book.data.repository.DefaultBookRepository
import com.sinharahul.bookpedia.book.domain.BookRepository
import com.sinharahul.bookpedia.book.presentation.book_list.BookListViewModel
import com.sinharahul.bookpedia.book.presentation.SelectedBookViewModel
import com.sinharahul.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.sinharahul.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)
}
