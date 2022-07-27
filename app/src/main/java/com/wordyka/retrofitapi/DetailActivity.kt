package com.wordyka.retrofitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    lateinit var tv_author: TextView
    lateinit var tv_publishedAt: TextView
    lateinit var tv_title: TextView
    lateinit var img_urlToImage: ImageView
    lateinit var tv_description: TextView
    lateinit var tv_content: TextView
    lateinit var tv_url: TextView

    lateinit var sp: SharePref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.title = "Detail Article"

        sp = SharePref(this)

        init()
    }

    private fun init() {
        tv_author = findViewById(R.id.tv_author)
        tv_publishedAt = findViewById(R.id.tv_publishedAt)
        tv_title = findViewById(R.id.tv_title)
        img_urlToImage = findViewById(R.id.img_urlToImage)
        tv_description = findViewById(R.id.tv_description)
        tv_content = findViewById(R.id.tv_content)
        tv_url = findViewById(R.id.tv_url)

        initArticle()
    }

    private fun initArticle() {
        tv_author.text = sp.getProduk()!!.author
        tv_publishedAt.text = sp.getProduk()!!.publishedAt
        tv_title.text = sp.getProduk()!!.title
        tv_description.text = sp.getProduk()!!.description
        tv_content.text = sp.getProduk()!!.content
        tv_url.text = sp.getProduk()!!.url

        Glide.with(this).load(sp.getProduk()!!.urlToImage).placeholder(R.drawable.social).into(img_urlToImage)
    }
}