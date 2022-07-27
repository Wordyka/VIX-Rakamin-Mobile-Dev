package com.wordyka.retrofitapi

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SharePref(activity: Activity) {
    val res = "res"

    val mypref = "MAIN_PREF"
    val sp:SharedPreferences

    init {
        sp = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    }

    fun setArticle(value: MainModel.Article) {
        val data:String = Gson().toJson(value, MainModel.Article::class.java)
        sp.edit().putString(res, data).apply()
    }

    fun getProduk():MainModel.Article? {
        val data:String = sp.getString(res, "") ?: return null
        val json = Gson().fromJson<MainModel.Article>(data, MainModel.Article::class.java)
        return json
    }
}