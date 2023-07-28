package com.example.loginmvvm.access

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.loginmvvm.R
import com.example.loginmvvm.access.signin.ui.SignInFragment
import com.example.loginmvvm.access.signup.ui.SignUpFragment
import com.example.loginmvvm.databinding.ActivityAccessBinding
import com.google.android.material.tabs.TabLayoutMediator


class AccessActivity : AppCompatActivity() {

    var email: String = String()
    var password: String = String()
    private lateinit var binding: ActivityAccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = AccessViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.listFragment = listOf(SignInFragment.newInstance(), SignUpFragment.newInstance())
        binding.viewPagerAccess.adapter = adapter

        TabLayoutMediator(binding.tabAccess, binding.viewPagerAccess) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_signin)
                1 -> tab.text = getString(R.string.title_signup)
            }
        }.attach()
    }

    fun gotoSignIn() {
        binding.viewPagerAccess.currentItem = 0
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(
            context, AccessActivity::class.java
        )
    }
}

class AccessViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    var listFragment: List<Fragment> = listOf()

    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}