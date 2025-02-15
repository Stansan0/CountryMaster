package com.example.countrymaster

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class UserDAO(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertUser(username: String, password: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_USERNAME, username)
            put(DatabaseHelper.COLUMN_PASSWORD, password)
        }
        val result = db.insert(DatabaseHelper.TABLE_USERS, null, values)
        db.close()
        return result
    }

    fun getAllUsers(): List<String> {
        val users = mutableListOf<String>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_USERS}", null)

        if (cursor.moveToFirst()) {
            do {
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME))
                users.add(username)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return users
    }

    fun updateUser(oldUsername: String, newUsername: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_USERNAME, newUsername)
        }
        val result = db.update(DatabaseHelper.TABLE_USERS, values, "${DatabaseHelper.COLUMN_USERNAME} = ?", arrayOf(oldUsername))
        db.close()
        return result
    }

    fun deleteUser(username: String): Int {
        val db = dbHelper.writableDatabase
        val result = db.delete(DatabaseHelper.TABLE_USERS, "${DatabaseHelper.COLUMN_USERNAME} = ?", arrayOf(username))
        db.close()
        return result
    }

    // 🔹 AGREGAR FUNCIÓN checkUser()
    fun checkUser(username: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM ${DatabaseHelper.TABLE_USERS} WHERE ${DatabaseHelper.COLUMN_USERNAME} = ? AND ${DatabaseHelper.COLUMN_PASSWORD} = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))

        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }
}
