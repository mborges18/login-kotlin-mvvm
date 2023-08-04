package com.example.loginmvvm.splash

import com.example.loginmvvm.ActionsRobot

object SplashAction {

    fun checkTitle() {
        ActionsRobot.checkTextIsDisplayed("Login Mvvm")
    }

    fun checkSubTitle() {
        ActionsRobot.checkTextIsDisplayed("Desenvolvido em Kotlin")
    }
}
