package com.example.loginmvvm.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loginmvvm.R
import com.example.loginmvvm.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handlerScreens()
    }

    private fun handlerScreens() {
        viewModel.getKeepLogged()

        viewModel.gotoHome.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_navigation_splash_to_navigation_home)
        }

        viewModel.gotoAccess.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_navigation_splash_to_navigation_access)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
