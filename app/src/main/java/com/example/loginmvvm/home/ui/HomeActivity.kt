package com.example.loginmvvm.home.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.loginmvvm.R
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.databinding.ActivityHomeBinding
import com.example.loginmvvm.home.model.CustomerModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handlerUiStateObserver()
    }

    private fun handlerUiStateObserver() {
        viewModel.getCustomer()

        viewModel.uiState.observe(this) {
            when(it) {
                is ResultState.Loading -> handlerShowLoading()
                is ResultState.Success -> handlerSuccess(it.data)
                is ResultState.Error -> handlerError()
                else -> handlerFailure()
            }
        }
    }

    private fun handlerShowLoading() {
        binding.loading.isVisible = true
    }

    private fun handlerHideLoading() {
        binding.loading.isVisible = false
    }

    private fun handlerSuccess(model: CustomerModel)= with(binding) {
        handlerHideLoading()
        model.apply {
            txvName.text = getString(R.string.s_name, name)
            txvBirthdate.text = getString(R.string.s_birthdate, birthdate)
            txvPhone.text = getString(R.string.s_phone, phone)
            txvEmail.text = getString(R.string.s_email, email)
            txvType.text = getString(R.string.s_type, type)
        }
    }

    private fun handlerError() {
        handlerHideLoading()
    }

    private fun handlerFailure() {
        handlerHideLoading()
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(
            context, HomeActivity::class.java
        )
    }
}
