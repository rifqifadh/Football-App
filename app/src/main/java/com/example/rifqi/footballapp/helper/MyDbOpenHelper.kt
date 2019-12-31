package com.example.rifqi.footballapp.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDbOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "FootballApp.db", null, 1) {

    companion object {
        private var instance: MyDbOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): MyDbOpenHelper {
            if (instance === null) {
                instance = MyDbOpenHelper(context)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable("TABLE_FAVORITE_MATCH", true,
            "ID_" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "EVENT_ID" to INTEGER + UNIQUE,
            "HOME_TEAM" to TEXT,
            "HOME_SCORE" to INTEGER,
            "AWAY_TEAM" to TEXT,
            "AWAY_SCORE" to INTEGER,
            "DATE_EVENT" to TEXT,
            "TIME_EVENT" to TEXT,
            "SPORT" to TEXT)

        db?.createTable("TABLE_FAVORITE_TEAM", true,
            "_ID" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "TEAM_ID" to TEXT + UNIQUE,
            "TEAM_BADGE" to TEXT,
            "TEAM_NAME" to TEXT,
            "TEAM_STADIUM" to TEXT,
            "TEAM_LEAGUE" to TEXT,
            "SPORT" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("TABLE_FAVORITE_MATCH", true)
        db?.dropTable("TABLE_FAVORITE_TEAM", true)
    }
}

// Access property for Context
val Context.database: MyDbOpenHelper
get() = MyDbOpenHelper.getInstance(applicationContext)