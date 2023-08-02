package com.unimind.unithing.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Comment
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Presenter.CommentPresenter
import com.unimind.unithing.R
import com.unimind.unithing.databinding.ItemCommentBinding
import com.unimind.unithing.databinding.ItemFeedBinding

class CommentNestedAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var commentList = mutableListOf<Comment>()
    var postItem = Post()

    lateinit var totalView: Map<Post, MutableList<Comment>>

    val POST_VIEW = 0
    val COMMENT_VIEW = 1

    private lateinit var itemFeedBinding: ItemFeedBinding
    private lateinit var itemCommentBinding: ItemCommentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            POST_VIEW -> {
                itemFeedBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_feed,
                    parent,
                    false
                )
                Log.d("Adapter", "onCreateViewHolder")
                PostViewHolder(itemFeedBinding)
            }

            COMMENT_VIEW -> {
                itemCommentBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_comment,
                    parent,
                    false
                )
                CommentViewHolder(itemCommentBinding)
            }

            else -> {
                throw RuntimeException("알 수 없는 viewtype error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")

        if (holder is PostViewHolder) {
            Log.d("Adapter", "onBindViewHolder PostViewHolder")
            holder.binding.itemFeedNicknameTv.text = postItem.nickname
            Log.d("Adapter", "nickname => ${postItem.nickname}")

            holder.binding.itemFeedBelongTv.text = "소속소속소속"

            holder.binding.itemFeedTitleTv.text = postItem.title
            Log.d("Adapter", "title => ${postItem.title}")

            holder.binding.itemFeedContentTv.text = postItem.content
            Log.d("Adapter", "content => ${postItem.content}")

        } else if (holder is CommentViewHolder) {
            holder.binding.itemCommentNicknameTv.text = commentList[position-1].user_nickname
            holder.binding.itemCommentBelongTv.text = commentList[position-1].user_belong
            holder.binding.itemCommentContentTv.text = commentList[position-1].content
        }
    }

    override fun getItemCount(): Int {
        return commentList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("Adapter", "getItemViewType position=> $position")

        return if (position == 0) {
            Log.d("Adapter", "POST_VIEW")
            POST_VIEW
        } else {
            Log.d("Adapter", "COMMENT_VIEW")
            COMMENT_VIEW
        }
    }

    inner class PostViewHolder(val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root)
    inner class CommentViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setComment(new: MutableList<Comment>) {
        Log.d("Adapter", "setComment")
        commentList = new
        notifyDataSetChanged()
    }

    fun setPost(new: Post) {
        Log.d("Adapter", "setPost")
        postItem = new
        notifyDataSetChanged()
    }
}