package com.example.customcontentprovider

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Cursor
        //(検索したいURI, 抽出したい項目, 絞り込み条件, 絞り込みパラメータ, ソート)
        val rs = contentResolver.query(
            AcronymProvider.CONTENT_URI,
            arrayOf(AcronymProvider.ID, AcronymProvider.NAME, AcronymProvider.MEANING),
            null,
            null,
            AcronymProvider.NAME
        )

        /**
         * Cursorを次へ
         */
        button.setOnClickListener {
            if (rs?.moveToNext()!!) {
                editText.setText(rs.getString(1))
                editText2.setText(rs.getString(2))
            }
        }

        /**
         * Cursorを前へ
         */
        button2.setOnClickListener {
            if (rs?.moveToPrevious()!!) {
                editText.setText(rs.getString(1))
                editText2.setText(rs.getString(2))
            }
        }

        /**
         * 項目を挿入
         */
        button3.setOnClickListener {
            val cv = ContentValues()
            cv.put(AcronymProvider.NAME, editText.text.toString())
            cv.put(AcronymProvider.MEANING, editText2.text.toString())
            contentResolver.insert(AcronymProvider.CONTENT_URI, cv)
            rs?.requery()
        }

        /**
         * 項目を更新
         */
        button4.setOnClickListener {
            val cv = ContentValues()
            cv.put(AcronymProvider.MEANING, editText2.text.toString())
            contentResolver.update(
                AcronymProvider.CONTENT_URI,
                cv,
                "NAME = ?",
                arrayOf(editText.text.toString())
            )
            rs?.requery()
        }

        /**
         * 項目を削除
         */
        button5.setOnClickListener {
            contentResolver.delete(
                AcronymProvider.CONTENT_URI,
                "NAME = ?",
                arrayOf(editText.text.toString())
            )
            rs?.requery()
        }

        /**
         * 項目を空に
         */
        button6.setOnClickListener {
            editText.setText("")
            editText2.setText("")
            //editTextにフォーカスを当てる
            editText.requestFocus()
        }

    }
}
