package com.example.loginmvvm

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.AccessActivityAction.checkInvalidFieldsSignIn
import com.example.loginmvvm.AccessActivityAction.checkHeaderSignIn
import com.example.loginmvvm.AccessActivityModule.getModuleSignUpSuccess
import com.example.loginmvvm.AccessActivityAction.checkHeaderSignUp
import com.example.loginmvvm.AccessActivityAction.clickButtonSignIn
import com.example.loginmvvm.AccessActivityAction.clickButtonSignUp
import com.example.loginmvvm.AccessActivityAction.clickTabSignUp
import com.example.loginmvvm.AccessActivityAction.typeInvalidDataSignIn
import com.example.loginmvvm.AccessActivityAction.typeDataSignUp
import com.example.loginmvvm.AccessActivityAction.typeValidDataSignIn
import com.example.loginmvvm.AccessActivityModule.getModuleSignInSuccess
import com.example.loginmvvm.access.AccessActivity
import io.mockk.clearAllMocks
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@SmallTest
class AccessActivityTest {

    @get:Rule
    val accessScenarioRule = ActivityScenarioRule(AccessActivity::class.java)

    @Test
    fun flow_make_signUp_to_signIn_success() {
        loadKoinModules(getModuleSignUpSuccess())

        clickTabSignUp()
        checkHeaderSignUp()
        typeDataSignUp()
        clickButtonSignUp()
        clickButtonSignIn()

        clearAllMocks()
        stopKoin()
    }

    @Test
    fun flow_to_make_signIn_with_invalid_data_to_signIn_success() {
        loadKoinModules(getModuleSignInSuccess())

        checkHeaderSignIn()
        typeInvalidDataSignIn()
        clickButtonSignIn()
        checkInvalidFieldsSignIn()
        typeValidDataSignIn()
        clickButtonSignIn()

        clearAllMocks()
        stopKoin()
    }
}
