package com.example.loginmvvm.access.signin.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.loginmvvm.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    @get:Rule
    var testSchedulerRule = InstantTaskExecutorRule()

    @Test
    fun `given open screen then show components`() {
        val scenario = launchFragmentInContainer<SignInFragment>(themeResId = R.style.AppTheme_NoActionBar)
        scenario.onFragment { fragment ->
            SignInFragmentRobot().start(fragment) {
                it.checkIsVisibleTitle()
                it.checkIsVisibleSubTitle()
                it.checkIsVisibleDescription()
                it.checkIsVisibleInputEmail()
                it.checkIsVisibleInputPassword()
                it.checkIsVisibleButton()
            }
        }
    }
}