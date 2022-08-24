package com.adisoft.mytweets

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

    private fun initComponents() {
        tweetEditText = findViewById(R.id.tweetEditText)
        tweetButton = findViewById(R.id.tweetButton)
        tweetListView = findViewById(R.id.tweetListView)
        tweets = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()

        tweets.add("Default Tweet")

        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@MainActivity,
            android.R.layout.simple_list_item_1,
            tweets as List<String?>
        )

        tweetListView.adapter = adapter

        tweetButton.setOnClickListener {
            val newTweet: String = tweetEditText.text.toString()

            if(newTweet.isNotEmpty()) {
                if (tweets.size == 1 && tweets[0] == "Default Tweet") tweets.clear()
                tweets.add(0, newTweet)
                adapter.notifyDataSetChanged()
            }
        }
    }
}