package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val item:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(item[viewHolder.adapterPosition])
        }
       return viewHolder
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.titleView.text = currentItem.title
    }
    fun updateNews(updatedNews: ArrayList<News>){
        item.clear()
        item.addAll(updatedNews)
        notifyDataSetChanged()
    }

}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}