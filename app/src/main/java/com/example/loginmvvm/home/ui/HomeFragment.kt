package com.example.loginmvvm.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loginmvvm.R
import com.example.loginmvvm.common.result.ResultState
import com.example.loginmvvm.databinding.FragmentHomeBinding
import com.example.loginmvvm.home.model.CustomerModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handlerUiStateObserver()
        handlerLogout()
    }

    private fun handlerUiStateObserver() {
        viewModel.getCustomer()

        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is ResultState.Loading -> handlerShowLoading()
                is ResultState.Success -> handlerSuccess(it.data)
                is ResultState.Error -> handlerError()
                else -> handlerFailure()
            }
        }

        viewModel.logout.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_navigation_home_to_navigation_access)
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

    private fun handlerLogout() {
        binding.btnLogout.setClickListener {
            viewModel.logout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
