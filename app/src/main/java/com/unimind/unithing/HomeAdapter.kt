package com.unimind.unithing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot

class HomeAdapter (var itemList: List<DocumentSnapshot>):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nickname.text = itemList[position].getString("nickname")
        holder.title.text = itemList[position].getString("title")
        holder.content.text = itemList[position].getString("content")
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nickname: TextView = view.findViewById(R.id.item_feed_nickname_tv)
        val title: TextView = view.findViewById(R.id.item_feed_title_tv)
        val content: TextView = view.findViewById(R.id.item_feed_content_tv)
    }
}