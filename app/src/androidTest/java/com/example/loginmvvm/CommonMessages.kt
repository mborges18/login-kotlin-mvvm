package com.example.loginmvvm

import com.example.loginmvvm.ActionsRobot.checkDialogIsVisible
import com.example.loginmvvm.ActionsRobot.closeDialog

object CommonMessages {

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
}
