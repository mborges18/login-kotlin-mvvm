package com.example.loginmvvm.home

import com.example.loginmvvm.ActionsRobot

object HomeAction {
    fun checkTitle() {
        ActionsRobot.checkTextIsDisplayed("Home")
    }

    fun checkName() {
        ActionsRobot.checkTextIsDisplayed("Nome: Marcos Paulo")
    }

    fun checkEmail() {
        ActionsRobot.checkTextIsDisplayed("E-mail: marcospaulo@gmail.com")
    }

    fun checkBirthDate() {
        ActionsRobot.checkTextIsDisplayed("Data de nascimento: 18/11/1981")
    }

    fun checkPhone() {
        ActionsRobot.checkTextIsDisplayed("Telefone: (81) 98634-1234")
    }

    fun checkTypeMember() {
        ActionsRobot.checkTextIsDisplayed("Tipo de cliente: GOLD")
    }

    fun checkLogoutButton() {
        ActionsRobot.checkTextIsDisplayed("Sair")
    }
}
