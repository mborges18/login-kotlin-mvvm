package com.example.loginmvvm.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.MainActivity
import com.example.loginmvvm.access.signin.SignInAction
import com.example.loginmvvm.home.HomeAction
import com.example.loginmvvm.home.HomeModule
import com.example.loginmvvm.splash.ui.SplashFragment
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@SmallTest
class SplashFragmentTest {

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun check_components_initial() {
        loadKoinModules(SplashModule.getModuleSplashNotKeepLogged(2000))
        ActivityScenario.launch(MainActivity::class.java)
        SplashAction.checkTitle()
        SplashAction.checkSubTitle()
    }

    @Test
    fun flow_not_keep_logged() {
        loadKoinModules(SplashModule.getModuleSplashNotKeepLogged())
        ActivityScenario.launch(MainActivity::class.java)
        SignInAction.checkTabSignIn()
        SignInAction.checkHeaderSignIn()
        SignInAction.checkFieldsSignIn()
        SignInAction.checkButtonNotClickableSignIn()
    }

    @Test
    fun flow_keep_logged() {
        loadKoinModules(
            listOf(
                SplashModule.getModuleSplashKeepLogged(),
                HomeModule.getModuleHomeDataCustomer()
            )
        )
        ActivityScenario.launch(MainActivity::class.java)
        HomeAction.checkTitle()
        HomeAction.checkName()
        HomeAction.checkEmail()
        HomeAction.checkBirthDate()
        HomeAction.checkPhone()
        HomeAction.checkTypeMember()
        HomeAction.checkLogoutButton()
    }
}
