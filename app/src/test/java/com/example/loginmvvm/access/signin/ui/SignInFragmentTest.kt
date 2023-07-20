package com.example.loginmvvm.access.signin.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.example.loginmvvm.R
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 29)
class SignInFragmentTest {

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `given open screen then show components`() {
        val cs = FragmentScenario.launchInContainer(SignInFragment::class.java, themeResId = R.style.AppTheme_NoActionBar)
        cs.onFragment { fragment ->
            SignInFragmentRobot().start(fragment) {
                it.checkIsVisibleTitle()
                it.checkIsVisibleSubTitle()
                it.checkIsVisibleDescription()
                it.checkIsVisibleInputEmail()
                it.checkIsVisibleInputPassword()
                it.checkIsVisibleButton()
            }
        }
//        val scenario = launchFragmentInContainer<SignInFragment>(themeResId = R.style.AppTheme_NoActionBar)
//        scenario.onFragment { fragment ->
//            SignInFragmentRobot().start(fragment) {
//                it.checkIsVisibleTitle()
//                it.checkIsVisibleSubTitle()
//                it.checkIsVisibleDescription()
//                it.checkIsVisibleInputEmail()
//                it.checkIsVisibleInputPassword()
//                it.checkIsVisibleButton()
//            }
//        }
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