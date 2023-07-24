package com.example.loginmvvm.access.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginmvvm.main.MainActivity
import com.example.loginmvvm.R
import com.example.loginmvvm.access.AccessActivity
import com.example.loginmvvm.access.signup.domain.SignUpModel
import com.example.loginmvvm.access.signup.domain.TypeMemberEnum
import com.example.loginmvvm.common.message.Message
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.databinding.FragmentSignupBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment: Fragment() {
    private val viewModel by viewModel<SignUpViewModel>()
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handlerDataSignUp()
        handlerUiStateObservers()
    }

    private fun handlerDataSignUp() {
        binding.cdtName.setListener {
            viewModel.setName(it)
        }
        binding.cdtBirthDate.setListener {
            viewModel.setBirthDate(it)
        }
        binding.cdtPhone.setListener {
            viewModel.setPhone(it)
        }
        binding.cdtEmail.setListener {
            viewModel.setEmail(it)
        }
        binding.cmpRadio.setData(
            arrayListOf(
                Pair(TypeMemberEnum.DISCIPLE.nameMember, true),
                Pair(TypeMemberEnum.LEADER.nameMember, false)
            )
        ) {
            viewModel.setTypeMember(it)
        }
        binding.cdtPassword.setListener {
            viewModel.setPassword(it)
        }
        binding.cdtConfirmPassword.setListener {
            viewModel.setConfirmPassword(it)
        }
        binding.btnSignup.setClickListener {
            viewModel.signUp()
        }
    }

    private fun handlerUiStateObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is ResultState.Loading -> handlerShowLoading()
                is ResultState.Error -> handlerMessageError()
                is ResultState.Failure -> handlerMessageFailure()
                is ResultState.NotFound -> Unit
                is ResultState.Success -> handlerDataSuccess(it.data as SignUpModel)
                is ResultState.Exists -> handlerDataExists()
            }
        }

        observerErrorEmail()
        observerErrorPassword()
        observerEnableButton()
    }

    private fun handlerShowLoading() = with(binding) {
        btnSignup.showLoading()
        cdtName.bindEnabled(false)
        cdtName.normalizeField()
        cdtBirthDate.bindEnabled(false)
        cdtBirthDate.normalizeField()
        cdtPhone.bindEnabled(false)
        cdtPhone.normalizeField()
        cdtEmail.bindEnabled(false)
        cdtEmail.normalizeField()
        cmpRadio.bindEnabled(false)
        cdtPassword.bindEnabled(false)
        cdtPassword.normalizeField()
        cdtConfirmPassword.bindEnabled(false)
        cdtConfirmPassword.normalizeField()
    }

    private fun handlerHideLoading() = with(binding) {
        btnSignup.hideLoading()
        cdtName.bindEnabled(true)
        cdtBirthDate.bindEnabled(true)
        cdtPhone.bindEnabled(true)
        cdtEmail.bindEnabled(true)
        cmpRadio.bindEnabled(true)
        cdtPassword.bindEnabled(true)
        cdtConfirmPassword.bindEnabled(true)
    }

    private fun handlerDataSuccess(model: SignUpModel) {
        (activity as? AccessActivity)?.apply {
            email = model.email
            password = model.password
        }?.also {
            it.gotoSignIn()
        }
    }

    private fun handlerDataExists() = with(binding) {
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
            binding.btnSignup.bindEnabled(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }
}
