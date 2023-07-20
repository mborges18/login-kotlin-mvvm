package com.example.loginmvvm.access.signin.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.loginmvvm.R
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.AfterClass
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
class SignInFragmentTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        clearAllMocks()
    }

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