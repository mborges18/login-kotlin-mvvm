package com.example.loginmvvm.access.signin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginmvvm.MainActivity
import com.example.loginmvvm.R
import com.example.loginmvvm.common.message.Message
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
        handlerSignIn()
        handlerObservers()
    }

    private fun handlerSignIn() {
        binding.cdtEmail.setListener {
            viewModel.setEmail(it)
        }
        binding.cdtPassword.setListener {
            viewModel.setPassword(it)
        }
        binding.btnSignIn.setClickListener {
            val keepLogged = binding.swtKeepLogged.isChecked
            viewModel.signIn(keepLogged)
        }
    }

    private fun handlerObservers() {
        observerLoading()
        observerEnableButton()
        observerEnableFields()
        observerSuccessSignIn()
        observerErrorSignIn()
        observerErrorEmail()
        observerErrorPassword()
    }

    private fun observerLoading(){
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.btnSignIn.showLoading()
            } else {
                binding.btnSignIn.hideLoading()
            }
        }
    }

    private fun observerEnableButton() {
        viewModel.enableButton.observe(viewLifecycleOwner) {
            binding.btnSignIn.bindEnabled(it)
        }
    }

    private fun observerEnableFields() {
        viewModel.enableFields.observe(viewLifecycleOwner) {
            binding.cdtEmail.bindEnabled(it)
            binding.cdtPassword.bindEnabled(it)
        }
    }

    private fun observerSuccessSignIn(){
        viewModel.signInSuccessResponse.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(MainActivity.newIntent(requireContext()))
                activity?.finish()
            }
        }
    }

    private fun observerErrorSignIn(){
        viewModel.signInNotFoundResponse.observe(viewLifecycleOwner) {
            if (it) {
                binding.cdtEmail.setError(getString(R.string.msg_error_user_not_found))
                binding.cdtPassword.setError(getString(R.string.msg_error_user_not_found))
            }
        }

        viewModel.signInErrorResponse.observe(viewLifecycleOwner) {
            if (it) {
                Message().dialog(
                    requireContext(),
                    Message.EType.ERROR,
                    getString(R.string.msg_error_generic)
                )
            }
        }

        viewModel.signInFailureResponse.observe(viewLifecycleOwner) {
            if (it) {
                Message().dialog(
                    requireContext(),
                    Message.EType.CONNECTION,
                    getString(R.string.msg_error_network)
                )
            }
        }
    }

    private fun observerErrorEmail(){
        viewModel.emailError.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtEmail.setError(getString(R.string.msg_invalid_field))
            }
        }
    }

    private fun observerErrorPassword(){
        viewModel.passwordError.observe(viewLifecycleOwner) {
            if(it) {
                binding.cdtPassword.setError(getString(R.string.msg_invalid_field))
            }
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
