package com.unimind.unithing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.unimind.unithing.Contract.UserContract
import com.unimind.unithing.Presenter.UserPresenter
import com.unimind.unithing.databinding.ActivityCertificationBinding

class CertificationActivity : AppCompatActivity(), UserContract.View {
    lateinit var binding : ActivityCertificationBinding
    val presenter = UserPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_certification)

        binding.activityCertificationBtn1.setOnClickListener {
            presenter.requestCreateDB()
        }
    }

    override fun showToast(message: String) {
        TODO("Not yet implemented")
    }

    override fun nextActivity() {
        this?.finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}