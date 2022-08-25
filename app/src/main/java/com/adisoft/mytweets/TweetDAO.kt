package com.adisoft.mytweets

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TweetDAO(context: Context) :
    SQLiteOpenHelper(context, "mytweets.db", null, 1) {

    private val tableName = "tweets"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $tableName(id INTEGER PRIMARY KEY AUTOINCREMENT, tweet varchar(200))")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("drop Table if exists $tableName")
    }

    fun getAll(): Cursor {
        return this.writableDatabase.rawQuery("SELECT * FROM $tableName", null)
    }

    fun get(id: Int): Cursor {
        return this.writableDatabase.rawQuery("SELECT * FROM $tableName WHERE id=$id", null)
    }

    fun insert(newTweet: ContentValues) {
        this.writableDatabase.insert(tableName, null, newTweet)
    }

    fun update(tweet: ContentValues) {
        this.writableDatabase.update(tableName, tweet, "id=${tweet.get("id")}", null)
    }

    fun delete(tweet: ContentValues) {
        this.writableDatabase.delete(tableName, "id=${tweet.get("id")}", null)
    }
}