package com.unimind.unithing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.unimind.unithing.Data.Post

class HomeAdapter (var itemList: MutableList<Post>):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nickname.text = itemList[position].nickname
        holder.title.text = itemList[position].title
        holder.content.text = itemList[position].content
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nickname: TextView = view.findViewById(R.id.item_feed_nickname_tv)
        val title: TextView = view.findViewById(R.id.item_feed_title_tv)
        val content: TextView = view.findViewById(R.id.item_feed_content_tv)
    }
    fun setData(new : MutableList<Post>){
        itemList = new
        notifyDataSetChanged()
    }
}