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

        binding.fragmentMypageNicknameEditIv.setOnClickListener {
            //닉네임 수정부를 구현해야하는데, Impl 구조에 대해서 논의가 필요. user DB에 접근하는 통합된 레포 생성에 관한 논의 필요
        }

        return binding.root
    }
}