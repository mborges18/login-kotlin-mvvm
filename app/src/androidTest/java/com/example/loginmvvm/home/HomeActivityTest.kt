package com.example.loginmvvm.home

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.home.ui.HomeActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@SmallTest
class HomeActivityTest {

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun check_components_initial() {
        loadKoinModules(HomeModule.getModuleHomeDataCustomer())
        ActivityScenario.launch(HomeActivity::class.java)
        HomeAction.checkTitle()
        HomeAction.checkName()
        HomeAction.checkEmail()
        HomeAction.checkBirthDate()
        HomeAction.checkPhone()
        HomeAction.checkTypeMember()
        HomeAction.checkLogoutButton()
    }
}
