package com.example.pirmp_10_makaryanaa.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getStringOrNull

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "authors.db", null, 1) {
    private val TABLE_NAME = "authors"
    private val COLUMN_ID = "id"
    private val COLUMN_AUTHOR = "author"
    private val COLUMN_POEM = "poem"
    private val COLUMN_CONTENT = "content"
    private val COLUMN_DATE = "date"

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
        "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_AUTHOR TEXT, $COLUMN_POEM TEXT, $COLUMN_CONTENT TEXT, $COLUMN_DATE INTEGER)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //Добавление нового автора
    fun addPoemAuthor(pa: PoemAuthor): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_AUTHOR, pa.author)
        cv.put(COLUMN_POEM, pa.poem)
        val result = db.insert(TABLE_NAME, null, cv)
        db.close()
        return result != -1L
    }
    // Удаление автора по стиху
    fun deletePoemAuthor(poem: String): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_POEM = ?", arrayOf(poem))
        db.close()
        return result > 0
    }

    // Поиск автора по стиху
    fun findPoemAuthor(poem: String): PoemAuthor? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, null, "$COLUMN_POEM = ?", arrayOf(poem), null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID))
                val author = it.getString(it.getColumnIndexOrThrow(COLUMN_AUTHOR))
                val poemText = it.getString(it.getColumnIndexOrThrow(COLUMN_POEM))
                val content = if (!it.isNull(it.getColumnIndexOrThrow(COLUMN_CONTENT))) it.getString(it.getColumnIndexOrThrow(COLUMN_CONTENT)) else null
                val date = if (!it.isNull(it.getColumnIndexOrThrow(COLUMN_DATE))) it.getInt(it.getColumnIndexOrThrow(COLUMN_DATE)) else null
                db.close()
                return PoemAuthor(id, author, poemText, content, date)
            }
        }
        db.close()
        return null
    }

    // Получение всех авторов
    fun getAllPoemAuthors(): MutableList<PoemAuthor> {
        val authorList = mutableListOf<PoemAuthor>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(0)
                    val author = it.getString(1)
                    val poem = it.getString(2)
                    val content = if (!it.isNull(3)) it.getString(3) else null
                    val date = if (!it.isNull(4)) it.getInt(4) else null
                    authorList.add(PoemAuthor(id, author, poem, content, date))
                } while (it.moveToNext())
            }
        }
        db.close()
        return authorList
    }
    fun updatePoemAuthor(oldPoem: String, newAuthor: String, newPoem: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_AUTHOR, newAuthor)
        cv.put(COLUMN_POEM, newPoem)
        val result = db.update(TABLE_NAME, cv, "$COLUMN_POEM = ?", arrayOf(oldPoem))
        db.close()
        return result > 0
    }

}