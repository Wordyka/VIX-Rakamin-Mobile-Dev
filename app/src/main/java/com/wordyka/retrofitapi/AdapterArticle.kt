package com.wordyka.retrofitapi

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class AdapterArticle(val article: ArrayList<MainModel.Article>,  val listener: OnAdapterListener): RecyclerView.Adapter<AdapterArticle.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from( parent.context ).inflate( R.layout.item_article,
            parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = article[position]
        holder.view.findViewById<TextView>(R.id.tv_title).text = pos.title
        holder.view.findViewById<TextView>(R.id.tv_author).text = pos.author
        Glide.with(holder.view).load(article[position].urlToImage).placeholder(R.drawable.social).into(holder.view.findViewById<ImageView>(R.id.img_urlToImage))

        holder.view.findViewById<CardView>(R.id.layout).setOnClickListener {
            listener.onClick(pos)
        }
    }

    override fun getItemCount() = article.size


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun setData(data: List<MainModel.Article>){
        this.article.clear()
        this.article.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: MainModel.Article)
    }

}