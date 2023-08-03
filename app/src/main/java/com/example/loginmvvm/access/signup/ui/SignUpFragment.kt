package com.example.loginmvvm.access.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginmvvm.R
import com.example.loginmvvm.access.AccessActivity
import com.example.loginmvvm.access.signup.model.SignUpModel
import com.example.loginmvvm.access.signup.model.TypeMemberEnum
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
        viewModel.setTypeMember(TypeMemberEnum.GOLD.nameMember)
        binding.cmpRadio.setData(
            arrayListOf(
                Pair(TypeMemberEnum.GOLD.nameMember, true),
                Pair(TypeMemberEnum.SILVER.nameMember, false),
                Pair(TypeMemberEnum.BRONZE.nameMember, false)
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
                is ResultState.Success -> handlerDataSuccess(it.data)
                is ResultState.Exists -> handlerDataExists()
            }
        }

        observerErrorName()
        observerErrorBirthDate()
        observerErrorPhone()
        observerErrorEmail()
        observerErrorPassword()
        observerErrorConfirmPassword()
        observerErrorDifferentPassword()
        observerEnableButton()
    }

    private fun handlerShowLoading() = with(binding) {
        btnSignup.showLoading()
        handlerEnableFields(isEnable = false)
        handlerNormalizeFields()

    }

    private fun handlerHideLoading() = with(binding) {
        btnSignup.hideLoading()
        handlerEnableFields(isEnable = true)
    }

    private fun handlerEnableFields(isEnable: Boolean) = with(binding) {
        cdtName.bindEnabled(isEnable)
        cdtBirthDate.bindEnabled(isEnable)
        cdtPhone.bindEnabled(isEnable)
        cdtEmail.bindEnabled(isEnable)
        cmpRadio.bindEnabled(isEnable)
        cdtPassword.bindEnabled(isEnable)
        cdtConfirmPassword.bindEnabled(isEnable)
    }

    private fun handlerNormalizeFields() = with(binding) {
        cdtName.normalizeField()
        cdtBirthDate.normalizeField()
        cdtPhone.normalizeField()
        cdtEmail.normalizeField()
        cdtPassword.normalizeField()
        cdtConfirmPassword.normalizeField()
    }

    private fun handlerClearFields() = with(binding) {
        cdtName.clearField()
        cdtBirthDate.clearField()
        cdtPhone.clearField()
        cdtEmail.clearField()
        cdtPassword.clearField()
        cdtConfirmPassword.clearField()
        handlerNormalizeFields()
    }

    private fun handlerDataSuccess(model: SignUpModel) {
        handlerHideLoading()
        (activity as? AccessActivity)?.apply {
            email = model.email
            password = model.password
        }?.also {
            it.gotoSignIn()
            handlerClearFields()
        }
    }

    private fun handlerDataExists() = with(binding) {
        handlerHideLoading()
        Message().dialog(
            requireContext(),
            Message.EType.ERROR,
            getString(R.string.msg_error_user_exists)
        )
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

    private fun observerErrorName() {
        viewModel.nameError.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtName.setError(getString(R.string.msg_invalid_field))
            }
        }
    }

    private fun observerErrorBirthDate() {
        viewModel.birthDateError.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtBirthDate.setError(getString(R.string.msg_invalid_field))
            }
        }
    }

    private fun observerErrorPhone() {
        viewModel.phoneError.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtPhone.setError(getString(R.string.msg_invalid_field))
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
                binding.cdtPassword.setError(getString(R.string.msg_invalid_password))
            }
        }
    }

    private fun observerErrorConfirmPassword() {
        viewModel.confirmPasswordError.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtConfirmPassword.setError(getString(R.string.msg_invalid_password))
            }
        }
    }

    private fun observerErrorDifferentPassword() {
        viewModel.differentPasswords.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtPassword.setError(getString(R.string.msg_different_password))
                binding.cdtConfirmPassword.setError(getString(R.string.msg_different_password))
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
