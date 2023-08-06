package com.example.loginmvvm.access.signup

import androidx.test.espresso.Espresso
import com.example.loginmvvm.ActionsRobot
import com.example.loginmvvm.R
import com.compdesign.R as RC

object SignUpAction {

    fun clickTabSignUp() {
        ActionsRobot.clickOnText(text = "CADASTRAR")
    }

    fun checkHeaderSignUp() {
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Cadastro")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Seja bem vindo!")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Informe seus dados para se cadastrar.")
    }

    fun checkFieldsSignUp() {
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Nome")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Nome")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Data de nascimento")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Telefone celular")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "E-mail")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Selecione uma opção:")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Ouro")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Prata")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Bronze")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Senha")
        ActionsRobot.checkTextIsDisplayedScrolling(text = "Confirme a senha")
    }

    fun checkButtonNotClickableSignUp() {
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.btn_signup,
            idChild = RC.id.cpb_title,
            text = "Cadastrar"
        )
        ActionsRobot.checkIdDescendantIsNotClickable(
            idParent = R.id.btn_signup,
            idChild = RC.id.cv_button
        )
    }

    fun typeDataSignUp() {
        clearDataSignUp()
        ActionsRobot.typeTextOnId(id = R.id.edt_name, text = "Marcio Borges")
        ActionsRobot.typeTextOnId(id = R.id.edt_birthdate, text = "18111981")
        ActionsRobot.typeTextOnId(id = R.id.edt_phone, text = "81986201853")
        ActionsRobot.typeTextOnId(id = R.id.edt_email, text = "marcioorges18@gmail.com")
        ActionsRobot.clickOnText(text = "Prata")
        ActionsRobot.typeTextOnId(id = R.id.edt_password, text = "A@123456")
        ActionsRobot.typeTextOnId(id = R.id.edt_confirm_password, text = "A@123456")
        Espresso.closeSoftKeyboard()
    }

    fun typeDataDifferentPasswordsSignUp() {
        clearDataSignUp()
        ActionsRobot.typeTextOnId(id = R.id.edt_name, text = "Marcio Borges")
        ActionsRobot.typeTextOnId(id = R.id.edt_birthdate, text = "18111981")
        ActionsRobot.typeTextOnId(id = R.id.edt_phone, text = "81986201853")
        ActionsRobot.typeTextOnId(id = R.id.edt_email, text = "marcioorges18@gmail.com")
        ActionsRobot.clickOnText(text = "Prata")
        ActionsRobot.typeTextOnId(id = R.id.edt_password, text = "123456")
        ActionsRobot.typeTextOnId(id = R.id.edt_confirm_password, text = "A@123456")
        Espresso.closeSoftKeyboard()
    }

    fun clearDataSignUp() {
        ActionsRobot.replaceTextOnId(id = R.id.edt_name, text = "")
        ActionsRobot.replaceTextOnId(id = R.id.edt_birthdate, text = "")
        ActionsRobot.replaceTextOnId(id = R.id.edt_phone, text = "")
        ActionsRobot.replaceTextOnId(id = R.id.edt_email, text = "")
        ActionsRobot.replaceTextOnId(id = R.id.edt_password, text = "")
        ActionsRobot.replaceTextOnId(id = R.id.edt_confirm_password, text = "")
    }

    fun typeInvalidDataSignUp() {
        ActionsRobot.typeTextOnId(id = R.id.edt_name, text = "Marcio")
        ActionsRobot.typeTextOnId(id = R.id.edt_birthdate, text = "1811198")
        ActionsRobot.typeTextOnId(id = R.id.edt_phone, text = "86201853")
        ActionsRobot.typeTextOnId(id = R.id.edt_email, text = "marcioorges18gmailcom")
        ActionsRobot.clickOnText(text = "Prata")
        ActionsRobot.typeTextOnId(id = R.id.edt_password, text = "456")
        ActionsRobot.typeTextOnId(id = R.id.edt_confirm_password, text = "456")
        Espresso.closeSoftKeyboard()
    }

    fun clickButtonSignUp() {
        ActionsRobot.clickOnId(id = R.id.btn_signup)
    }

    fun checkInvalidFieldsSignUp() {
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_name,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_birth_date,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_phone,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_email,
            idChild = RC.id.tvError,
            text = "Campo inválido"
        )
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_password,
            idChild = RC.id.tvError,
            text = "A senha deve ter de 6 a 12 caracteres"
        )
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_confirm_password,
            idChild = RC.id.tvError,
            text = "A senha deve ter de 6 a 12 caracteres"
        )
    }

    fun checkDifferentPasswordsSignUp() {
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_password,
            idChild = RC.id.tvError,
            text = "As senhas devem ser iguais"
        )
        ActionsRobot.checkIdDescendantWithText(
            idParent = R.id.cdt_confirm_password,
            idChild = RC.id.tvError,
            text = "As senhas devem ser iguais"
        )
    }
}
