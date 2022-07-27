package com.wordyka.retrofitapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private lateinit var adapterArticle: AdapterArticle
    private lateinit var rvArticle: RecyclerView
    lateinit var sp: SharePref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "List of Article"

        sp = SharePref(this)

        setupList()
        getArticle()
    }

    override fun onStart() {
        super.onStart()
        getArticle()
    }


    private fun setupList() {
        adapterArticle = AdapterArticle(arrayListOf(), object : AdapterArticle.OnAdapterListener {
            override fun onClick(articles: MainModel.Article) {
                sp.setArticle(articles)
                startActivity(
                    Intent(this@MainActivity, DetailActivity::class.java)
                        .putExtra("intent", articles.title)
                )
            }


        })
        rvArticle = findViewById(R.id.list_article)

        rvArticle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterArticle
        }
    }

    private fun getArticle() {
        ApiConfig.endpoint.getData()
            .enqueue(object : Callback<MainModel> {
                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    printLog(t.toString())
                }

                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    if (response.isSuccessful) {
                        showResult(response.body()!!)
                    }
                }
            })
    }

    private fun showResult(results: MainModel) {
        for (result in results.articles) printLog("title: ${result.title}")
        adapterArticle.setData(results.articles)
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }
}