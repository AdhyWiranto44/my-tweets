package com.adisoft.mytweets

import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var tweetEditText: EditText
    private lateinit var tweetButton: Button
    private lateinit var tweetListView: ListView
    private lateinit var tweets: ArrayList<String>
    private lateinit var db: TweetDAO

    private fun initComponents() {
        tweetEditText = findViewById(R.id.tweetEditText)
        tweetButton = findViewById(R.id.tweetButton)
        tweetListView = findViewById(R.id.tweetListView)
        tweets = ArrayList()
        db = TweetDAO(this)
    }

    private fun renderData() {
        val datas: Cursor = db.getAll()
        while (datas.moveToNext()) tweets.add(0,datas.getString(1).toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        renderData()

        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@MainActivity,
            android.R.layout.simple_list_item_1,
            tweets as List<String?>
        )

        tweetListView.adapter = adapter

        tweetButton.setOnClickListener {
            val newTweet = ContentValues()
            newTweet.put("tweet", tweetEditText.text.toString())
            db.insert(newTweet)
            tweets.add(0, tweetEditText.text.toString())
            tweetEditText.text.clear()

            adapter.notifyDataSetChanged()
        }
    }
}