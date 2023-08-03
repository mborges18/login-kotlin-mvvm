package com.example.loginmvvm.access.signin


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import com.example.loginmvvm.AccessActivityAction.checkMessageError
import com.example.loginmvvm.AccessActivityAction.clickButtonSignIn
import com.example.loginmvvm.AccessActivityAction.typeValidDataSignIn
import com.example.loginmvvm.AccessActivityModule.getModuleSignInError
import com.example.loginmvvm.access.AccessActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@SmallTest
class SignInErrorTest {

    @Suppress("DEPRECATION")
    @get:Rule
    val activityRule = ActivityTestRule(AccessActivity::class.java, true, false)

    @Before
    fun setup() {
        loadKoinModules(getModuleSignInError())
        activityRule.launchActivity(null)
    }

    @After
    fun teardown() {
        stopKoin()
        activityRule.finishActivity()
    }

    @Test
    fun flow_to_make_signIn_messages_error() {
        typeValidDataSignIn()
        clickButtonSignIn()
        checkMessageError()
    }
}