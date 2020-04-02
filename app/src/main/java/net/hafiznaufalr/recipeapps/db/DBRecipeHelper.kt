package net.hafiznaufalr.recipeapps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBRecipeHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        var DATABASE_NAME = "favorite"
        private val DATABASE_VERSION = 1
    }

    private val SQL_CREATE_TABLE_RECIPE = String.format(
        "CREATE TABLE %s"
                + " (%s TEXT PRIMARY KEY NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
        RecipeContract().TABLE_RECIPE,
        RecipeContract.MovieColumns.IDMEAL,
        RecipeContract.MovieColumns.STRMEAL,
        RecipeContract.MovieColumns.STRMEALTHUMB

    )

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_RECIPE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + RecipeContract().TABLE_RECIPE)
    }
}