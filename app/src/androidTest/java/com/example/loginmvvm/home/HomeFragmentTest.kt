package com.example.loginmvvm.home

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.MainActivity
import com.example.loginmvvm.home.ui.HomeFragment
import com.example.loginmvvm.splash.SplashModule
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@SmallTest
class HomeFragmentTest {

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun check_components_initial() {
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
