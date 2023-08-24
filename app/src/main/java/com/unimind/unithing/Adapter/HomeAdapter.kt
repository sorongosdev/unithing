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
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Presenter.CommentPresenter
import com.unimind.unithing.R

class HomeAdapter (var itemList: MutableList<Post>, listener: CommentContract.View):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var commentPresenter = CommentPresenter(listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nickname.text = itemList[position].nickname
        holder.belong.text = itemList[position].belong
        holder.title.text = itemList[position].title
        holder.content.text = itemList[position].content
        holder.commentBtn.setOnClickListener {
            mCallback.showCommentActivity()
            commentPresenter.savePostInfo(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private val mCallback = listener

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nickname: TextView = view.findViewById(R.id.item_feed_nickname_tv)
        val belong: TextView = view.findViewById(R.id.item_feed_belong_tv)
        val title: TextView = view.findViewById(R.id.item_feed_title_tv)
        val content: TextView = view.findViewById(R.id.item_feed_content_tv)
        val commentBtn: ImageView = view.findViewById(R.id.item_feed_comment_iv)
    }
    fun setData(new : MutableList<Post>){
        itemList = new
        notifyDataSetChanged()
    }
}