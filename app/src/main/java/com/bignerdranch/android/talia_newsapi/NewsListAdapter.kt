package com.bignerdranch.android.talia_newsapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.talia_newsapi.databinding.NewsItemsBinding

class NewsListAdapter(
    private var articles: MutableList<Article>,
    private val onClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.ArticleViewHolder>() {

    fun updateArticles(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = NewsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }
    override fun getItemCount(): Int = articles.size
    inner class ArticleViewHolder(private val binding: NewsItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.articleTitle.text = article.title
            binding.articleDescription.text = article.description ?: ""
            itemView.setOnClickListener {
                onClick(article)
            }
        }
    }
}