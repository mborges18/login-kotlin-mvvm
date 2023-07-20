package com.example.loginmvvm.access.signin.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.loginmvvm.R
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    @Ignore("Test is ignored as a demonstration")
    @Test
    fun `given open screen then show components`() {
        Assert.assertEquals(true, true)
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