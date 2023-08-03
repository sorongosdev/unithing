package com.unimind.unithing

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.Presenter.PostPresenter
import com.unimind.unithing.Presenter.UserInfoPresenter
import com.unimind.unithing.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity(), PostContract.View{
    private lateinit var binding: ActivityPostBinding
    private lateinit var presenter: PostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        presenter = PostPresenter(this)

        binding.activityPostPostBtn.setOnClickListener {
            val title = binding.activityWriteTitleEt.text.toString()
            val content = binding.activityWriteContentEt.text.toString()
            presenter.post(title,content)
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun nextActivity() {
        this.finish()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
    }
}