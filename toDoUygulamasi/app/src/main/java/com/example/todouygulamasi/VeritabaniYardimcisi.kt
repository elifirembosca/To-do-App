package com.example.todouygulamasi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context: Context)
    : SQLiteOpenHelper(context,"todouygulamasi.sqlite",null,1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS \"yapilacaklar\" (\n" +
                "\t\"yapilacak_id\"\tINTEGER,\n" +
                "\t\"yapilacak_is\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"yapilacak_id\" AUTOINCREMENT)\n" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS yapilacaklar")
        onCreate(db)
    }

}

