package com.unimind.unithing

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.unimind.unithing.Contract.SignUserContract
import com.unimind.unithing.Presenter.SignUserPresenter
import com.unimind.unithing.databinding.FragmentSignupBinding

class SignUpFragment : Fragment(), SignUserContract.View {
    lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

//        //유효성 검사
//        checkValidation()

        val presenter = SignUserPresenter(this)

        binding.fragmentSignupEmailTiet.addTextChangedListener {
            val email = binding.fragmentSignupEmailTiet.text.toString()
            presenter.checkValidation(email)
        }
        binding.fragmentSignupSignupBtn.setOnClickListener {
            val userEmail = binding.fragmentSignupEmailTiet.text.toString()
            val userPassword = binding.fragmentSignupPasswordTiet.text.toString()
            presenter.requestSignUp(userEmail, userPassword)
            presenter.requestSignUp(userEmail, userPassword)
        }

        return binding.root
    }

//    /**이메일아이디, 비번 유효성 검사 구현*/
//    override fun checkValidation() {
//        //이메일 주소 형식 갖추어야함
//        binding.fragmentSignupEmailTiet.addTextChangedListener {
//            val email = binding.fragmentSignupEmailTiet.text.toString()
//            val pattern = Patterns.EMAIL_ADDRESS
//            binding.fragmentSignupEmailTil.error =
//                if (pattern.matcher(email).matches()) null
//                else "이메일 주소 형식을 입력해주세요."
//        }
//        //비밀번호
//        binding.fragmentSignupPasswordTiet.addTextChangedListener {
//            it?.let { text ->
//                binding.fragmentSignupPasswordTil.error = if (text.length < 8) "8자 이상 입력해주세요" else null
//            }
//        }
//    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message,Toast.LENGTH_LONG).show()
    }

    override fun nextActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showValidation(errorMsg : String?) {
        binding.fragmentSignupEmailTil.error = errorMsg
    }
}