package com.example.kanba

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Clase para manejar la base de datos de usuarios
class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Definir la estructura de la tabla de usuarios
    private val createUserTable = "CREATE TABLE $tableName (" +
            "$columnId INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$columnUsername TEXT," +
            "$columnPassword TEXT)"

    // Método para insertar un nuevo usuario
    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(columnUsername, user.username)
        values.put(columnPassword, user.password)
        db.insert(tableName, null, values)
        db.close()
    }

    // Método para verificar si un usuario existe
    fun checkUser(username: String, password: String): Boolean {
        val columns = arrayOf(columnId)
        val db = this.readableDatabase
        val selection = "$columnUsername = ? AND $columnPassword =?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(tableName, columns, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()
        db.close()
        return count > 0
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createUserTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDB"
        private const val tableName = "users"
        private const val columnId = "id"
        private const val columnUsername = "username"
        private const val columnPassword = "password"
    }
}

// Clase que representa un usuario
data class User(val id: Int = -1, val username: String, val password: String)
