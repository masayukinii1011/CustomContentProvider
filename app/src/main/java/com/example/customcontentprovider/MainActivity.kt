package com.example.customcontentprovider

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rs = contentResolver.query(
            AcronymProvider.CONTENT_URI,
            arrayOf(AcronymProvider._ID, AcronymProvider.NAME, AcronymProvider.MEANING),
            null,
            null,
            AcronymProvider.NAME
        )

        button.setOnClickListener {
            if (rs?.moveToNext()!!) {
                editText.setText(rs.getString(1))
                editText2.setText(rs.getString(2))
            }
        }

        button2.setOnClickListener {
            if (rs?.moveToPrevious()!!) {
                editText.setText(rs.getString(1))
                editText2.setText(rs.getString(2))
            }
        }

        button3.setOnClickListener {
            var cv = ContentValues()
            cv.put(AcronymProvider.NAME, editText.text.toString())
            cv.put(AcronymProvider.MEANING, editText2.text.toString())
            contentResolver.insert(AcronymProvider.CONTENT_URI, cv)
            rs?.requery()
        }

        button4.setOnClickListener {
            var cv = ContentValues()
            cv.put(AcronymProvider.MEANING, editText2.text.toString())
            contentResolver.update(
                AcronymProvider.CONTENT_URI,
                cv,
                "NAME = ?",
                arrayOf(editText.text.toString())
            )
            rs?.requery()
        }


        button5.setOnClickListener {
            contentResolver.delete(
                AcronymProvider.CONTENT_URI,
                "NAME = ?",
                arrayOf(editText.text.toString())
            )
            rs?.requery()
        }

        button6.setOnClickListener {
            editText.setText("")
            editText2.setText("")
            editText.requestFocus()
        }

    }
}
