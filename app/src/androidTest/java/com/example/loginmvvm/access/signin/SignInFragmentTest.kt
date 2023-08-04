package com.example.loginmvvm.access.signin

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.loginmvvm.AccessActivityAction
import com.example.loginmvvm.AccessActivityModule
import com.example.loginmvvm.access.AccessActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@MediumTest
class SignInFragmentTest {

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun flow_to_make_signIn_messages_error() {
        loadKoinModules(AccessActivityModule.getModuleSignInNotFound())
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.typeDataSignIn()
        AccessActivityAction.clickButtonSignIn()
        AccessActivityAction.checkMessageEmailNotFound()

        loadKoinModules(AccessActivityModule.getModuleSignInError())
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.typeDataSignIn()
        AccessActivityAction.clickButtonSignIn()
        AccessActivityAction.checkMessageError()

        loadKoinModules(AccessActivityModule.getModuleSignInFailure())
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.typeDataSignIn()
        AccessActivityAction.clickButtonSignIn()
        AccessActivityAction.checkMessageFailure()

        loadKoinModules(AccessActivityModule.getModuleSignInSuccess())
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.typeInvalidDataSignIn()
        AccessActivityAction.clickButtonSignIn()
        AccessActivityAction.checkInvalidFieldsSignIn()
        AccessActivityAction.typeDataSignIn()
        AccessActivityAction.clickButtonSignIn()
    }
}
