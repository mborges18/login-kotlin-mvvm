package com.example.loginmvvm.access.signin

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.loginmvvm.access.AccessActivity
import com.example.loginmvvm.access.CommonMessages
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
    fun check_components_initial() {
        ActivityScenario.launch(AccessActivity::class.java)
        SignInAction.checkTabSignIn()
        SignInAction.checkHeaderSignIn()
        SignInAction.checkFieldsSignIn()
        SignInAction.checkButtonNotClickableSignIn()
    }

    @Test
    fun flow_to_make_signIn_messages_error() {
        loadKoinModules(SignInModule.getModuleSignInNotFound())
        ActivityScenario.launch(AccessActivity::class.java)
        SignInAction.typeDataSignIn()
        SignInAction.clickButtonSignIn()
        SignInAction.checkMessageEmailNotFound()

        loadKoinModules(SignInModule.getModuleSignInError())
        ActivityScenario.launch(AccessActivity::class.java)
        SignInAction.typeDataSignIn()
        SignInAction.clickButtonSignIn()
        CommonMessages.checkMessageError()

        loadKoinModules(SignInModule.getModuleSignInFailure())
        ActivityScenario.launch(AccessActivity::class.java)
        SignInAction.typeDataSignIn()
        SignInAction.clickButtonSignIn()
        CommonMessages.checkMessageFailure()

        loadKoinModules(SignInModule.getModuleSignInSuccess())
        ActivityScenario.launch(AccessActivity::class.java)
        SignInAction.typeInvalidDataSignIn()
        SignInAction.clickButtonSignIn()
        SignInAction.checkInvalidFieldsSignIn()
        SignInAction.typeDataSignIn()
        SignInAction.clickButtonKeepLogged()
        SignInAction.clickButtonSignIn()
    }
}
