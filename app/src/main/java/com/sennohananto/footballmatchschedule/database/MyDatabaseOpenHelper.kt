package com.sennohananto.footballmatchschedule.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.EVENT_ID to TEXT + UNIQUE,
                FavoriteMatch.AWAY_SCORE to TEXT,
                FavoriteMatch.DATE_MATCH to TEXT,
                FavoriteMatch.HOME_NAME to TEXT,
                FavoriteMatch.HOME_SCORE to TEXT,
                FavoriteMatch.AWAY_NAME to TEXT,
                FavoriteMatch.STR_TIME to TEXT
        )

        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM,true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.ID_TEAM to TEXT + UNIQUE,
                FavoriteTeam.STR_TEAM to TEXT,
                FavoriteTeam.INT_FORMED_YEAR to TEXT,
                FavoriteTeam.ID_LEAGUE to TEXT,
                FavoriteTeam.STR_STADIUM to TEXT,
                FavoriteTeam.STR_DESCRIPTION_EN to TEXT,
                FavoriteTeam.STR_TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM,true)
    }
}

    val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)