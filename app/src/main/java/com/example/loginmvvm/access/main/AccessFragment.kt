package com.example.loginmvvm.access.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.loginmvvm.R
import com.example.loginmvvm.access.signin.ui.SignInFragment
import com.example.loginmvvm.access.signup.ui.SignUpFragment
import com.example.loginmvvm.databinding.FragmentAccessBinding
import com.google.android.material.tabs.TabLayoutMediator

class AccessFragment : Fragment(), OnGoto {

    private var _binding: FragmentAccessBinding? = null
    private val binding get() = _binding!!
    var email: String = String()
    var password: String = String()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = AccessViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.listFragment = listOf(SignInFragment.newInstance(), SignUpFragment.newInstance())
        binding.viewPagerAccess.adapter = adapter

        TabLayoutMediator(binding.tabAccess, binding.viewPagerAccess) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_tab_signin)
                1 -> tab.text = getString(R.string.title_tab_signup)
            }
        }.attach()
    }

    override fun gotoSignIn() {
        binding.viewPagerAccess.currentItem = 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
