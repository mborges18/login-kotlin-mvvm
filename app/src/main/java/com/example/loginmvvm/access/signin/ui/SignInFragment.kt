package com.example.loginmvvm.access.signin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginmvvm.main.MainActivity
import com.example.loginmvvm.R
import com.example.loginmvvm.access.AccessActivity
import com.example.loginmvvm.common.message.Message
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.databinding.FragmentSigninBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment: Fragment() {
    private val viewModel by viewModel<SignInViewModel>()
    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handlerDataSignIn()
        handlerUiStateObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fillFieldsAfterSignUp()
    }

    private fun handlerDataSignIn() {
        binding.cdtEmail.setListener{
            viewModel.setEmail(it)
        }
        binding.cdtPassword.setListener {
            viewModel.setPassword(it)
        }
        binding.btnSignin.setClickListener {
            val keepLogged = binding.swtKeepLogged.isChecked
            viewModel.signIn(keepLogged)
        }
    }

    private fun handlerUiStateObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is ResultState.Loading -> handlerShowLoading()
                is ResultState.Error -> handlerMessageError()
                is ResultState.Failure -> handlerMessageFailure()
                is ResultState.NotFound -> handlerDataNotFound()
                is ResultState.Success -> handlerDataSuccess()
                is ResultState.Exists -> Unit
            }
        }

        observerFillFieldsAfterSignUp()
        observerErrorEmail()
        observerErrorPassword()
        observerEnableButton()
    }

    private fun handlerShowLoading() = with(binding) {
        btnSignin.showLoading()
        cdtEmail.bindEnabled(false)
        cdtEmail.normalizeField()
        cdtPassword.bindEnabled(false)
        cdtPassword.normalizeField()
    }

    private fun handlerHideLoading() = with(binding) {
        btnSignin.hideLoading()
        cdtEmail.bindEnabled(true)
        cdtPassword.bindEnabled(true)
    }

    private fun handlerDataSuccess() {
        startActivity(MainActivity.newIntent(requireContext()))
        activity?.finish()
    }

    private fun handlerDataNotFound() = with(binding) {
        handlerHideLoading()
        cdtEmail.setError(getString(R.string.msg_error_user_not_found))
        cdtPassword.setError(getString(R.string.msg_error_user_not_found))
    }

    private fun handlerMessageError() {
        handlerHideLoading()
        Message().dialog(
            requireContext(),
            Message.EType.ERROR,
            getString(R.string.msg_error_generic)
        )
    }
    private fun handlerMessageFailure() {
        handlerHideLoading()
        Message().dialog(
            requireContext(),
            Message.EType.CONNECTION,
            getString(R.string.msg_error_network)
        )
    }

    private fun observerFillFieldsAfterSignUp() {
        viewModel.fillFieldsAfterSignUp.observe(viewLifecycleOwner) {
            if (it) {
                val email  = (activity as AccessActivity).email
                val password = (activity as AccessActivity).password
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    binding.cdtEmail.setData(email)
                    binding.cdtPassword.setData(password)
                }
            }
        }
    }

    private fun observerErrorEmail() {
        viewModel.emailError.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtEmail.setError(getString(R.string.msg_invalid_field))
            }
        }
    }

    private fun observerErrorPassword() {
        viewModel.passwordError.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtPassword.setError(getString(R.string.msg_invalid_field))
            }
        }
    }

    private fun observerEnableButton() {
        viewModel.enableButton.observe(viewLifecycleOwner) {
            binding.btnSignin.bindEnabled(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }
}
