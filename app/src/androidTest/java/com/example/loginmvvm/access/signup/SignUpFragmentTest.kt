package com.example.loginmvvm.access.signup

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.CommonMessages
import com.example.loginmvvm.MainActivity
import com.example.loginmvvm.access.signin.SignInAction
import com.example.loginmvvm.access.signin.SignInModule
import com.example.loginmvvm.home.HomeModule
import com.example.loginmvvm.splash.SplashModule
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@SmallTest
class SignUpFragmentTest : KoinTest {

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun check_components_initial() {
        loadKoinModules(SplashModule.getModuleSplashNotKeepLogged())
        ActivityScenario.launch(MainActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.checkHeaderSignUp()
        SignUpAction.checkFieldsSignUp()
        SignUpAction.checkButtonNotClickableSignUp()
    }

    @Test
    fun flow_signup_and_singin_success() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashNotKeepLogged(),
                SignUpModule.getModuleSignUpSuccess(),
                SignInModule.getModuleSignInSuccess(),
                HomeModule.getModuleHomeDataCustomer()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
        SignInAction.clickButtonSignIn()
    }

    @Test
    fun flow_signup_email_already_exists() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashNotKeepLogged(),
                SignUpModule.getModuleSignUpExists()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
        CommonMessages.checkMessageEmailExists()
    }

    @Test
    fun flow_signup_error() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashNotKeepLogged(),
                SignUpModule.getModuleSignUpError()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
        CommonMessages.checkMessageError()
    }

    @Test
    fun flow_signup_failure() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashNotKeepLogged(),
                SignUpModule.getModuleSignUpFailure()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
        CommonMessages.checkMessageFailure()
    }

    @Test
    fun flow_signup_invalid_fields() {
        loadKoinModules(SplashModule.getModuleSplashNotKeepLogged())
        ActivityScenario.launch(MainActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeInvalidDataSignUp()
        SignUpAction.clickButtonSignUp()
        SignUpAction.checkInvalidFieldsSignUp()
    }

    @Test
    fun flow_signup_different_passwords() {
        loadKoinModules(SplashModule.getModuleSplashNotKeepLogged())
        ActivityScenario.launch(MainActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataDifferentPasswordsSignUp()
        SignUpAction.clickButtonSignUp()
        SignUpAction.checkDifferentPasswordsSignUp()
    }
}
