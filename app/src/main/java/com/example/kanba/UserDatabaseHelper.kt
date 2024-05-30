package com.example.kanba

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_USERS (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)")
        // Agregar algunos usuarios de ejemplo para propósitos de prueba
        db.execSQL("INSERT INTO $TABLE_USERS (username, password) VALUES ('test', 'password')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun checkUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE username = ? AND password = ?", arrayOf(username, password))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun addUser(username: String, password: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("username", username)
            put("password", password)
        }
        val result = db.insert(TABLE_USERS, null, values)
        return result != -1L
    }

    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"
    }
}
