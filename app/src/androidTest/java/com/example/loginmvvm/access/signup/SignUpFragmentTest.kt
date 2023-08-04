package com.example.loginmvvm.access.signup

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.access.AccessActivity
import com.example.loginmvvm.access.CommonMessages
import com.example.loginmvvm.access.signin.SignInAction
import com.example.loginmvvm.access.signin.SignInModule
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

        ActivityScenario.launch(AccessActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.checkHeaderSignUp()
        SignUpAction.checkFieldsSignUp()
        SignUpAction.checkButtonNotClickableSignUp()
    }

    @Test
    fun flow_make_signUp_to_signIn_success() {
        loadKoinModules(
            listOf(
                SignUpModule.getModuleSignUpSuccess(),
                SignInModule.getModuleSignInSuccess()
            )
        )
        ActivityScenario.launch(AccessActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
        SignInAction.clickButtonSignIn()
    }

    @Test
    fun flow_to_make_signUp_messages_error() {
        loadKoinModules(SignUpModule.getModuleSignUpExists())
        ActivityScenario.launch(AccessActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
        CommonMessages.checkMessageEmailExists()

        loadKoinModules(SignUpModule.getModuleSignUpError())
        ActivityScenario.launch(AccessActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
        CommonMessages.checkMessageError()

        loadKoinModules(SignUpModule.getModuleSignUpFailure())
        ActivityScenario.launch(AccessActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
        CommonMessages.checkMessageFailure()

        loadKoinModules(SignUpModule.getModuleSignUpSuccess())
        ActivityScenario.launch(AccessActivity::class.java)
        SignUpAction.clickTabSignUp()
        SignUpAction.typeInvalidDataSignUp()
        SignUpAction.clickButtonSignUp()
        SignUpAction.checkInvalidFieldsSignUp()
        SignUpAction.typeDataDifferentPasswordsSignUp()
        SignUpAction.clickButtonSignUp()
        SignUpAction.checkDifferentPasswordsSignUp()
        SignUpAction.typeDataSignUp()
        SignUpAction.clickButtonSignUp()
    }
}
