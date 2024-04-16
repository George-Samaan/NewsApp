package com.route.newsapp.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.route.newsapp.api.model.newsResponse.Article
import com.route.newsapp.databinding.ItemNewsBinding

class NewsAdapter(var newsList: List<Article?>?) : Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = newsList?.size ?: 0

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList?.get(position))
    }

    fun changeData(articles: List<Article?>?) {
        newsList = articles
        notifyDataSetChanged()
    }


    class NewsViewHolder(val itemBinding: ItemNewsBinding) : ViewHolder(itemBinding.root) {
        fun bind(news: Article?) {
            itemBinding.title.text = news?.title
            itemBinding.author.text = news?.author
            itemBinding.date.text = news?.publishedAt
            Glide.with(itemView)
                .load(news?.urlToImage)
                .into(itemBinding.image)
        }

    }
}