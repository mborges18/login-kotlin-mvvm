package com.example.loginmvvm.access.signup

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.AccessActivityAction
import com.example.loginmvvm.AccessActivityModule
import com.example.loginmvvm.access.AccessActivity
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
    fun flow_make_signUp_to_signIn_success() {
        loadKoinModules(
            listOf(
                AccessActivityModule.getModuleSignUpSuccess(),
                AccessActivityModule.getModuleSignInSuccess()
            )
        )
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.clickTabSignUp()
        AccessActivityAction.typeDataSignUp()
        AccessActivityAction.clickButtonSignUp()
        AccessActivityAction.clickButtonSignIn()
    }

    @Test
    fun flow_to_make_signUp_messages_error(){
        loadKoinModules(AccessActivityModule.getModuleSignUpExists())
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.clickTabSignUp()
        AccessActivityAction.typeDataSignUp()
        AccessActivityAction.clickButtonSignUp()
        AccessActivityAction.checkMessageEmailExists()

        loadKoinModules(AccessActivityModule.getModuleSignUpError())
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.clickTabSignUp()
        AccessActivityAction.typeDataSignUp()
        AccessActivityAction.clickButtonSignUp()
        AccessActivityAction.checkMessageError()

        loadKoinModules(AccessActivityModule.getModuleSignUpFailure())
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.clickTabSignUp()
        AccessActivityAction.typeDataSignUp()
        AccessActivityAction.clickButtonSignUp()
        AccessActivityAction.checkMessageFailure()

        loadKoinModules(AccessActivityModule.getModuleSignUpSuccess())
        ActivityScenario.launch(AccessActivity::class.java)
        AccessActivityAction.clickTabSignUp()
        AccessActivityAction.typeInvalidDataSignUp()
        AccessActivityAction.clickButtonSignUp()
        AccessActivityAction.checkInvalidFieldsSignUp()
        AccessActivityAction.typeDataSignUp()
        AccessActivityAction.clickButtonSignUp()
    }
}
