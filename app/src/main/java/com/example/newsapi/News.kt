package com.example.newsapi

import androidx.room.Entity
import androidx.room.PrimaryKey

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Source(
    @PrimaryKey val id: Int,
    val name: String,
)
@Entity
data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)
