package com.unimind.unithing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.unimind.unithing.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}