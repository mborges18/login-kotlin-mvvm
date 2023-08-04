package com.example.loginmvvm.access.signin

import androidx.test.espresso.Espresso
import com.example.loginmvvm.ActionsRobot
import com.example.loginmvvm.R

object SignInAction {

    fun checkTabSignIn() {
        ActionsRobot.checkTextIsDisplayed("Acesso")
    }

    fun checkHeaderSignIn() {
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Acesso")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Seja bem vindo!")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Informe seu e-mail e senha para acessar à sua área.")
    }

    fun checkFieldsSignIn() {
        ActionsRobot.checkTextIsDisplayedScrolling(text = "E-mail")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Senha")
    }

    fun checkButtonNotClickableSignIn() {
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.btn_signin,
            idChild = com.compdesign.R.id.cpb_title,
            text = "Entrar"
        )
        ActionsRobot.checkIdDescendantIsNotClickable(
            idParent = R.id.btn_signin,
            idChild = com.compdesign.R.id.cv_button
        )
    }

    fun typeInvalidDataSignIn() {
        ActionsRobot.typeTextOnId(id = R.id.edt_email, text = "marcioorges18gmail.com")
        ActionsRobot.typeTextOnId(id = R.id.edt_password, text = "A56")
        Espresso.closeSoftKeyboard()
    }

    fun checkInvalidFieldsSignIn() {
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_email,
            idChild = com.compdesign.R.id.tvError,
            text = "Campo inválido"
        )
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_password,
            idChild = com.compdesign.R.id.tvError,
            text = "Campo inválido"
        )
    }

    fun typeDataSignIn() {
        clearDataSignIn()
        ActionsRobot.typeTextOnId(id = R.id.edt_email, text = "marcioorges18@gmail.com")
        ActionsRobot.typeTextOnId(id = R.id.edt_password, text = "A@123456")
        Espresso.closeSoftKeyboard()
    }

    fun clearDataSignIn() {
        ActionsRobot.replaceTextOnId(id = R.id.edt_email, text = "")
        ActionsRobot.replaceTextOnId(id = R.id.edt_password, text = "")
    }

    fun clickButtonSignIn() {
        ActionsRobot.clickOnId(id = R.id.btn_signin)
    }

    fun checkMessageEmailNotFound() {
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_email,
            idChild = com.compdesign.R.id.tvError,
            text = "Usuário ou senha inválido"
        )
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_password,
            idChild = com.compdesign.R.id.tvError,
            text = "Usuário ou senha inválido"
        )
    }

    fun clickButtonKeepLogged() {
        ActionsRobot.clickOnId(id = R.id.swt_keep_logged)
    }
}
