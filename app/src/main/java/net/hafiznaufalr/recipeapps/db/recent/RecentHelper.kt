package net.hafiznaufalr.recipeapps.db.recent

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import net.hafiznaufalr.recipeapps.db.DBRecipeHelper
import net.hafiznaufalr.recipeapps.db.bookmark.BookmarkContract
import net.hafiznaufalr.recipeapps.db.recent.RecentContract.RecentColumns.Companion.IDMEAL
import net.hafiznaufalr.recipeapps.db.recent.RecentContract.RecentColumns.Companion.STRMEAL
import net.hafiznaufalr.recipeapps.db.recent.RecentContract.RecentColumns.Companion.STRMEALTHUMB
import net.hafiznaufalr.recipeapps.db.recent.RecentContract.RecentColumns.Companion.TIMEVIEWED
import net.hafiznaufalr.recipeapps.model.Filter
import net.hafiznaufalr.recipeapps.model.Recent
import java.sql.SQLException

class RecentHelper(context: Context) {
    val DATABASE_TABLE = RecentContract().TABLE_RECENT
    var databaseHelper = DBRecipeHelper(context)
    lateinit var database: SQLiteDatabase

    companion object {

        @Volatile
        private var INSTANCE: RecentHelper? = null

        fun getInstance(context: Context): RecentHelper =
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: RecentHelper(
                            context
                        )
                }
    }


    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun getAllRecent(): ArrayList<Filter> {
        val arrayList = ArrayList<Filter>()
        val cursor = database.query(
            DATABASE_TABLE,
            null, null, null, null, null,
            TIMEVIEWED, null
        )
        cursor.moveToFirst()
        var recent: Filter
        if (cursor.count > 0) {
            do {
                recent = Filter(
                    cursor.getString(cursor.getColumnIndexOrThrow(IDMEAL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(STRMEAL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(STRMEALTHUMB))
                )

                arrayList.add(recent)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun isViewed(id: String): Boolean {
        val cursor = database.query(
            DATABASE_TABLE,
            null, "$IDMEAL = '$id'", null, null, null,
            null, null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
            return true
        }
        return false
    }

    fun updateDate(id: String, time: String): Int{
        val args = ContentValues()
        args.put(IDMEAL, id)
        args.put(TIMEVIEWED, time)

        return database.update(DATABASE_TABLE, args, "$IDMEAL = '$id'", null )
    }

    fun insertRecent(recent: Recent): Long {
        val args = ContentValues()
        args.put(IDMEAL, recent.idMeal)
        args.put(STRMEAL, recent.strMeal)
        args.put(STRMEALTHUMB, recent.strMealThumb)
        args.put(TIMEVIEWED, recent.timeViewed)

        return database.insert(DATABASE_TABLE, null, args)
    }

    fun clearRecent(): Int {
        return database.delete(RecentContract().TABLE_RECENT, null, null)
    }

}