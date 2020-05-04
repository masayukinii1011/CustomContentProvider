package com.example.customcontentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context?) : SQLiteOpenHelper(context, "ACDB", null, 1) {

    /**
     * DBを作成。項目を挿入
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ACTABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MEANING TEXT)")
        db?.execSQL("INSERT INTO ACTABLE(NAME,MEANING) VALUES('MCA', 'Master of Computer Applications')")
        db?.execSQL("INSERT INTO ACTABLE(NAME,MEANING) VALUES('BCA', 'Bachelor of Computer Applications')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}