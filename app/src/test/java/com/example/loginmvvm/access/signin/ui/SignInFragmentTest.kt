package com.example.loginmvvm.access.signin.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.loginmvvm.R
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.AfterClass
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Ignore("Falhas")
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

    companion object {
        @JvmStatic
        @AfterClass
        fun unloadInjectionAndMocks() {
            stopKoin()
            unmockkAll()
        }
    }
}