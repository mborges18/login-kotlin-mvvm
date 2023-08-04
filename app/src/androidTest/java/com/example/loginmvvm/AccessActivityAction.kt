package com.example.loginmvvm

import androidx.test.espresso.Espresso.closeSoftKeyboard
import com.example.loginmvvm.ActionsRobot.checkDialogIsVisible
import com.example.loginmvvm.ActionsRobot.checkIdDescendantWithText
import com.example.loginmvvm.ActionsRobot.checkTextIsDisplayed
import com.example.loginmvvm.ActionsRobot.clickOnText
import com.example.loginmvvm.ActionsRobot.clickOnId
import com.example.loginmvvm.ActionsRobot.closeDialog
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
        clearDataSignUp()
        typeTextOnId(id = R.id.edt_name, text = "Marcio Borges")
        typeTextOnId(id = R.id.edt_birthdate, text = "18111981")
        typeTextOnId(id = R.id.edt_phone, text = "81986201853")
        typeTextOnId(id = R.id.edt_email, text = "marcioorges18@gmail.com")
        clickOnText(text = "Prata")
        typeTextOnId(id = R.id.edt_password, text = "A@123456")
        typeTextOnId(id = R.id.edt_confirm_password, text = "A@123456")
        closeSoftKeyboard()
    }

    fun clearDataSignUp() {
        replaceTextOnId(id = R.id.edt_name, text = "")
        replaceTextOnId(id = R.id.edt_birthdate, text = "")
        replaceTextOnId(id = R.id.edt_phone, text = "")
        replaceTextOnId(id = R.id.edt_email, text = "")
        replaceTextOnId(id = R.id.edt_password, text = "")
        replaceTextOnId(id = R.id.edt_confirm_password, text = "")
    }

    fun typeInvalidDataSignUp() {
        typeTextOnId(id = R.id.edt_name, text = "Marcio")
        typeTextOnId(id = R.id.edt_birthdate, text = "1811198")
        typeTextOnId(id = R.id.edt_phone, text = "86201853")
        typeTextOnId(id = R.id.edt_email, text = "marcioorges18gmailcom")
        clickOnText(text = "Prata")
        typeTextOnId(id = R.id.edt_password, text = "456")
        typeTextOnId(id = R.id.edt_confirm_password, text = "456")
        closeSoftKeyboard()
    }

    fun clickButtonSignUp() {
        clickOnId(id = R.id.btn_signup)
    }

    fun checkInvalidFieldsSignUp() {
        checkIdDescendantWithText(
            idParent = R.id.cdt_name,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        checkIdDescendantWithText(
            idParent = R.id.cdt_birth_date,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        checkIdDescendantWithText(
            idParent = R.id.cdt_phone,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        checkIdDescendantWithText(
            idParent = R.id.cdt_email,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        checkIdDescendantWithText(
            idParent = R.id.cdt_password,
            idChild = RC.id.tvError,
            text = "A senha deve ter de 6 a 12 caracteres"
        )
        checkIdDescendantWithText(
            idParent = R.id.cdt_confirm_password,
            idChild = RC.id.tvError,
            text = "A senha deve ter de 6 a 12 caracteres"
        )
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
        closeSoftKeyboard()
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

    fun typeDataSignIn() {
        clearDataSignIn()
        typeTextOnId(id = R.id.edt_email, text = "marcioorges18@gmail.com")
        typeTextOnId(id = R.id.edt_password, text = "A@123456")
        closeSoftKeyboard()
    }

    fun clearDataSignIn() {
        replaceTextOnId(id = R.id.edt_email, text = "")
        replaceTextOnId(id = R.id.edt_password, text = "")
    }

    fun clickButtonSignIn() {
        clickOnId(id = R.id.btn_signin)
    }

    // * MESSAGES
    fun checkMessageEmailExists() {
        checkDialogIsVisible(text = "E-mail já registrado. Informe outro e-mail, por favor!")
        closeDialog(R.id.btnOk)
    }

    fun checkMessageError() {
        checkDialogIsVisible(text = "Ops! Ocorreu um erro inesperado, tente novamente mais tarde, por favor!")
        closeDialog(R.id.btnOk)
    }

    fun checkMessageFailure() {
        checkDialogIsVisible(text = "Ops! Ocorreu um erro na rede, por favor, verifique sua conexão!")
        closeDialog(R.id.btnOk)
    }

    fun checkMessageEmailNotFound() {
        checkIdDescendantWithText(
            idParent = R.id.cdt_email,
            idChild = RC.id.tvError,
            text = "Usuário ou senha inválido"
        )
        checkIdDescendantWithText(
            idParent = R.id.cdt_password,
            idChild = RC.id.tvError,
            text = "Usuário ou senha inválido"
        )
    }

    fun clearFieldsSignIn() {
        replaceTextOnId(id = R.id.edt_email, text = "")
        replaceTextOnId(id = R.id.edt_password, text = "")
    }

}
