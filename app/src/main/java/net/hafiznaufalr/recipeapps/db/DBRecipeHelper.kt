package net.hafiznaufalr.recipeapps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import net.hafiznaufalr.recipeapps.db.bookmark.BookmarkContract
import net.hafiznaufalr.recipeapps.db.recent.RecentContract

class DBRecipeHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        var DATABASE_NAME = "recipe"
        private val DATABASE_VERSION = 1
    }

    private val SQL_CREATE_TABLE_BOOKMRK = String.format(
        "CREATE TABLE %s"
                + " (%s TEXT PRIMARY KEY NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
        BookmarkContract().TABLE_BOOKMARK,
        BookmarkContract.BookmarkColumns.IDMEAL,
        BookmarkContract.BookmarkColumns.STRMEAL,
        BookmarkContract.BookmarkColumns.STRMEALTHUMB
    )

    private val SQL_CREATE_TABLE_RECENT = String.format(
        "CREATE TABLE %s"
                + " (%s TEXT PRIMARY KEY NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
        RecentContract().TABLE_RECENT,
        RecentContract.RecentColumns.IDMEAL,
        RecentContract.RecentColumns.STRMEAL,
        RecentContract.RecentColumns.STRMEALTHUMB,
        RecentContract.RecentColumns.TIMEVIEWED
    )

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_BOOKMRK)
        db?.execSQL(SQL_CREATE_TABLE_RECENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + BookmarkContract().TABLE_BOOKMARK)
        db?.execSQL("DROP TABLE IF EXISTS " + RecentContract().TABLE_RECENT)

    }
}