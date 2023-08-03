package com.example.loginmvvm.access.signup

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import com.example.loginmvvm.AccessActivityAction.checkHeaderSignIn
import com.example.loginmvvm.AccessActivityAction.checkHeaderSignUp
import com.example.loginmvvm.AccessActivityAction.checkInvalidFieldsSignIn
import com.example.loginmvvm.AccessActivityAction.clickButtonSignIn
import com.example.loginmvvm.AccessActivityAction.clickButtonSignUp
import com.example.loginmvvm.AccessActivityAction.clickTabSignUp
import com.example.loginmvvm.AccessActivityAction.insertDataSignIn
import com.example.loginmvvm.AccessActivityAction.typeDataSignUp
import com.example.loginmvvm.AccessActivityAction.typeInvalidDataSignIn
import com.example.loginmvvm.AccessActivityAction.typeValidDataSignIn
import com.example.loginmvvm.AccessActivityModule.getModuleSignInSuccess
import com.example.loginmvvm.AccessActivityModule.getModuleSignUpSuccess
import com.example.loginmvvm.access.AccessActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@SmallTest
class SignUpToSignInTest : KoinTest {

    @Suppress("DEPRECATION")
    @get:Rule
    val activityRule = ActivityTestRule(AccessActivity::class.java, true, false)

    @Before
    fun setup() {
        loadKoinModules(
            listOf(
                getModuleSignUpSuccess(),
                getModuleSignInSuccess()
            )
        )
        activityRule.launchActivity(null)
    }

    @After
    fun teardown() {
        stopKoin()
        activityRule.finishActivity()
    }

    @Test
    fun flow_make_signUp_to_signIn_success() {
        clickTabSignUp()
        checkHeaderSignUp()
        typeDataSignUp()
        clickButtonSignUp()
        clickButtonSignIn()
    }
}
