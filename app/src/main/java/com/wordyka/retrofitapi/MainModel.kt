package com.wordyka.retrofitapi

data class MainModel(
    val articles: ArrayList<Article>
    ) {
    data class Article (var author: String,
                       var title: String,
                       var description: String,
                       var url: String,
                       var urlToImage: String,
                       var publishedAt: String,
                       var content: String
                       )
}