package com.unimind.unithing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.unimind.unithing.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {
    private lateinit var binding : FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        return binding.root
    }
}