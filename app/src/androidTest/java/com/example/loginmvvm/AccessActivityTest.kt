package com.example.loginmvvm

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.loginmvvm.AccessActivityRobot.getModuleData
import com.example.loginmvvm.access.AccessActivity
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@SmallTest
class AccessActivityTest {

    @get:Rule
    val accessScenarioRule = ActivityScenarioRule(AccessActivity::class.java)

    @Before
    fun setUp() {
        loadKoinModules(getModuleData())
    }

    @After
    fun setDown() {
        clearAllMocks()
        stopKoin()
    }

    @Test
    fun flowMakeSignUpToSignIn() {
        onView(withId(R.id.tab_access)).check(matches(isDisplayed()))

        onView(withText("CADASTRAR")).perform(click())
        onView(withId(R.id.edt_name)).perform(typeText("Marcio Borges"))
        onView(withId(R.id.edt_birthdate)).perform(typeText("18111981"))
        onView(withId(R.id.edt_phone)).perform(scrollTo(), typeText("81986201853"))
        onView(withId(R.id.edt_email)).perform(scrollTo(), typeText("marcioorges18@gmail.com"))
        onView(withText("Prata")).perform(scrollTo(), click())
        onView(withId(R.id.edt_password)).perform(scrollTo(), typeText("A@123456"))
        onView(withId(R.id.edt_confirm_password)).perform(scrollTo(), typeText("A@123456"))

        onView(withId(R.id.btn_signup)).perform(scrollTo(), click())

        onView(withId(R.id.btn_signin)).perform(scrollTo(), click())
    }
}