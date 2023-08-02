package com.example.loginmvvm

import com.example.loginmvvm.ActionsRobot.checkIdDescendantWithText
import com.example.loginmvvm.ActionsRobot.checkTextIsDisplayed
import com.example.loginmvvm.ActionsRobot.clickOnText
import com.example.loginmvvm.ActionsRobot.clickOnId
import com.example.loginmvvm.ActionsRobot.replaceTextOnId
import com.example.loginmvvm.ActionsRobot.typeTextOnId
import com.compdesign.R as RC

object AccessActivityAction {

    // * SIGNUP
    fun clickTabSignUp() {
        clickOnText(text = "CADASTRAR")
    }

    fun checkHeaderSignUp() {
        checkTextIsDisplayed(text = "Cadastro")
        checkTextIsDisplayed(text = "Seja bem vindo!")
        checkTextIsDisplayed(text = "Informe seus dados para se cadastrar.")
    }

    fun typeDataSignUp() {
        typeTextOnId(id = R.id.edt_name, text = "Marcio Borges")
        typeTextOnId(id = R.id.edt_birthdate, text = "18111981")
        typeTextOnId(id = R.id.edt_phone, text = "81986201853")
        typeTextOnId(id = R.id.edt_email, text = "marcioorges18@gmail.com")
        clickOnText(text = "Prata")
        typeTextOnId(id = R.id.edt_password, text = "A@123456")
        typeTextOnId(id = R.id.edt_confirm_password, text = "A@123456")
    }

    fun clickButtonSignUp() {
        clickOnId(id = R.id.btn_signup)
    }

    fun clickButtonSignIn() {
        clickOnId(id = R.id.btn_signin)
    }

    // * SIGNIN
    fun checkHeaderSignIn() {
        checkTextIsDisplayed(text = "Acesso")
        checkTextIsDisplayed(text = "Seja bem vindo!")
        checkTextIsDisplayed(text = "Informe seu e-mail e senha para acessar à sua área.")
    }

    fun typeInvalidDataSignIn() {
        typeTextOnId(id = R.id.edt_email, text = "marcioorges18gmail.com")
        typeTextOnId(id = R.id.edt_password, text = "A56")
    }

    fun checkInvalidFieldsSignIn() {
        checkIdDescendantWithText(
            idParent = R.id.cdt_email,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        checkIdDescendantWithText(
            idParent = R.id.cdt_password,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
    }

    fun typeValidDataSignIn() {
        replaceTextOnId(id = R.id.edt_email, text = "marcioorges18@gmail.com")
        replaceTextOnId(id = R.id.edt_password, text = "A@123456")
    }
}