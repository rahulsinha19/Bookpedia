package com.sinharahul.bookpedia.book.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val content: Context
) {
    actual fun create(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val appContext = content.applicationContext
        val dbFile = appContext.getDatabasePath(FavoriteBookDatabase.DATABASE_NAME)

        return Room.databaseBuilder(context = appContext, name = dbFile.absolutePath)
    }
}
