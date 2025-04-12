package com.sinharahul.bookpedia.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object BookGraph

    @Serializable
    data object BookList : Route

    @Serializable
    data class BookDetail(val bookId: String) : Route
}
