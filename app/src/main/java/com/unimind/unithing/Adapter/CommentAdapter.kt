package com.unimind.unithing.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Comment
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Presenter.CommentPresenter
import com.unimind.unithing.R

class CommentAdapter (var itemList: MutableList<Comment>):
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nickname.text = itemList[position].user_nickname
        holder.belong.text = itemList[position].user_belong
        holder.content.text = itemList[position].content
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nickname: TextView = view.findViewById(R.id.item_comment_nickname_tv)
        val belong: TextView = view.findViewById(R.id.item_comment_belong_tv)
        val content: TextView = view.findViewById(R.id.item_comment_content_tv)

    }
    fun setData(new : MutableList<Comment>){
        itemList = new
        notifyDataSetChanged()
    }
}