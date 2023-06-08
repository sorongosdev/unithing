package com.unimind.unithing

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
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

        val presenter = SignUserPresenter(this)

        binding.fragmentSignupEmailTiet.addTextChangedListener {
            val email = binding.fragmentSignupEmailTiet.text.toString()
            presenter.checkValidation(email)
        }
        binding.fragmentSignupSignupBtn.setOnClickListener {
            val userEmail = binding.fragmentSignupEmailTiet.text.toString()
            val userPassword = binding.fragmentSignupPasswordTiet.text.toString()
            val selectedRadioButtonId = binding.fragmentSignupRg.checkedRadioButtonId
            val userType = binding.fragmentSignupRg.findViewById<RadioButton>(selectedRadioButtonId).text.toString()
            
            presenter.requestSignUp(userEmail, userPassword, userType)
        }

        return binding.root
    }

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