package com.unimind.unithing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.unimind.unithing.databinding.ActivityCommentBinding

class CommentActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment)

    }
}