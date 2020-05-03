package com.example.customcontentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class AcronymProvider : ContentProvider() {

    companion object {
        val PROVIDER_NAME = "com.example.customcontentprovider/AcronymProvider"
        val URL = "contact://$PROVIDER_NAME/ACTABLE"
        val CONTENT_URI = Uri.parse(URL)

        val _ID = "_id"
        val NAME = "NAME"
        val MEANING = "MEANING"
    }

    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        var helper = MyHelper(context)
        db = helper.writableDatabase
        return if (db == null) false else true
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert("ACTABLE", null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun update(
        uri: Uri,
        cv: ContentValues?,
        condition: String?,
        condition_val: Array<out String>?
    ): Int {
        var count = db.update("ACTABLE", cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        var count = db.delete("ACTABLE", condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query("ACTABLE", cols,condition,condition_val,null,null,order)
    }

    override fun getType(uri: Uri): String? {
return "vnd.android.cursor.dir/vnd.example.actable"
    }

}