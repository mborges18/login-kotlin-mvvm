package com.example.loginmvvm.access.signin

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.loginmvvm.CommonMessages
import com.example.loginmvvm.MainActivity
import com.example.loginmvvm.splash.SplashModule
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
        loadKoinModules(SplashModule.getModuleSplashNotKeepLogged())
        ActivityScenario.launch(MainActivity::class.java)
        SignInAction.checkTabSignIn()
        SignInAction.checkHeaderSignIn()
        SignInAction.checkFieldsSignIn()
        SignInAction.checkButtonNotClickableSignIn()
    }

    @Test
    fun flow_signin_success() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashNotKeepLogged(),
                SignInModule.getModuleSignInSuccess()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        SignInAction.typeDataSignIn()
        SignInAction.clickButtonKeepLogged()
        SignInAction.clickButtonSignIn()
    }

    @Test
    fun flow_signin_not_found() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashNotKeepLogged(),
                SignInModule.getModuleSignInNotFound()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        SignInAction.typeDataSignIn()
        SignInAction.clickButtonSignIn()
        SignInAction.checkMessageEmailNotFound()
    }

    @Test
    fun flow_signin_error() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashNotKeepLogged(),
                SignInModule.getModuleSignInError()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        SignInAction.typeDataSignIn()
        SignInAction.clickButtonSignIn()
        CommonMessages.checkMessageError()
    }

    @Test
    fun flow_signin_failure() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashNotKeepLogged(),
                SignInModule.getModuleSignInFailure()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        SignInAction.typeDataSignIn()
        SignInAction.clickButtonSignIn()
        CommonMessages.checkMessageFailure()
    }

    @Test
    fun flow_signin_invalid_fields() {
        loadKoinModules(SplashModule.getModuleSplashNotKeepLogged())
        ActivityScenario.launch(MainActivity::class.java)
        SignInAction.typeInvalidDataSignIn()
        SignInAction.clickButtonSignIn()
        SignInAction.checkInvalidFieldsSignIn()
    }
}
