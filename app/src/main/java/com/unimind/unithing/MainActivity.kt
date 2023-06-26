package com.unimind.unithing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.Presenter.AuthorityPresenter
import com.unimind.unithing.Presenter.UserInfoPresenter
import com.unimind.unithing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , UserInfoContract.View {
    private lateinit var binding : ActivityMainBinding
    private lateinit var presenter: UserInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.activityMainBnv.setupWithNavController(navController)

        Log.d("Main","MainActivity")
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun nextActivity() {
        TODO("Not yet implemented")
    }

    override fun isAuthorized() {
        showToast("isAuthorized")
    }

    override fun notAuthorized() {
        showToast("notAuthorized")
    }
}