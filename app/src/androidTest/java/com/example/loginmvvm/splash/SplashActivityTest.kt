package com.example.loginmvvm.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.splash.ui.SplashActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@SmallTest
class SplashActivityTest {

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun check_components_initial() {
        ActivityScenario.launch(SplashActivity::class.java)
        SplashAction.checkTitle()
        SplashAction.checkSubTitle()
    }
}
