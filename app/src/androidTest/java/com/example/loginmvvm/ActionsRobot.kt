package com.example.loginmvvm

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.AllOf.allOf

object ActionsRobot {

    fun checkIdIsDisplayed(@IdRes id: Int) {
        onView(withId(id)).perform(scrollTo()).check(matches(isDisplayed()))
    }

    fun checkTextIsDisplayed(text: String) {
        onView(withText(text)).perform(scrollTo()).check(matches(isDisplayed()))
    }

    fun checkIdIsEnabled(@IdRes id: Int) {
        onView(withId(id)).perform(scrollTo()).check(matches(isEnabled()))
    }

    fun checkIdIsNotEnabled(@IdRes id: Int) {
        onView(withId(id)).perform(scrollTo()).check(matches(isNotEnabled()))
    }

    fun checkIdDescendantIsEnabled(@IdRes idParent: Int, @IdRes idChild: Int) {
        onView(
            allOf(
                withId(idChild),
                ViewMatchers.isDescendantOfA(withId(idParent))
            )
        ).perform(scrollTo()).check(matches(isEnabled()))
    }

    fun checkIdDescendantIsNotEnabled(@IdRes idParent: Int, @IdRes idChild: Int) {
        onView(
            allOf(
                withId(idChild),
                ViewMatchers.isDescendantOfA(withId(idParent))
            )
        ).perform(scrollTo()).check(matches(isNotEnabled()))
    }

    fun checkIdDescendantWithText(@IdRes idParent: Int, @IdRes idChild: Int, text: String) {
        onView(
            allOf(
                withId(idChild),
                ViewMatchers.isDescendantOfA(withId(idParent))
            )
        ).perform(scrollTo()).check(matches(withText(text)))
    }

    fun clickOnId(@IdRes id: Int) {
        onView(withId(id)).perform(scrollTo(), click())
    }

    fun clickOnText(text: String) {
        onView(withText(text)).perform(scrollTo(), click())
    }

    fun typeTextOnId(@IdRes id: Int, text: String) {
        onView(withId(id)).perform(scrollTo(), typeText(text))
    }

    fun replaceTextOnId(@IdRes id: Int, text: String) {
        onView(withId(id)).perform(scrollTo(), replaceText(text))
    }

    fun checkDialogIsVisible(text: String) {
        onView(withText(text))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    fun closeDialog(@IdRes id: Int,) {
        onView(withId(id))
            .inRoot(isDialog())
            .perform(click())
    }
}