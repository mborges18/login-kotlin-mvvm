package com.example.loginmvvm.access.signin.ui

import android.widget.TextView
import androidx.core.view.isVisible
import com.compdesign.compbutton.CompPrimeButton
import com.example.loginmvvm.R as RL
import com.compdesign.R as RC
import com.compdesign.compedittext.CompEditTextInput
import com.google.android.material.switchmaterial.SwitchMaterial
import org.junit.Assert.assertEquals

class SignInFragmentRobot {

    private var fragment: SignInFragment? = null

    fun start(fragment: SignInFragment, func: (robot: SignInFragmentRobot) -> Unit) {
        this.fragment = fragment
        func.invoke(this)
    }

    fun checkIsVisibleTitle() {
        assertEquals(true, fragment?.view?.findViewById<TextView>(RC.id.txv_title)?.isVisible)
        assertEquals(
            "Acessar",
            fragment?.view?.findViewById<TextView>(RC.id.txv_title)?.text.toString()
        )
    }

    fun checkIsVisibleSubTitle() {
        assertEquals(
            true,
            fragment?.view?.findViewById<TextView>(RC.id.txv_subtitle)?.isVisible
        )
        assertEquals(
            "Seja bem vindo!",
            fragment?.view?.findViewById<TextView>(RC.id.txv_subtitle)?.text.toString()
        )
    }

    fun checkIsVisibleDescription() {
        assertEquals(
            true,
            fragment?.view?.findViewById<TextView>(RC.id.txv_description)?.isVisible
        )
        assertEquals(
            "Informe seu e-mail e senha para acessar a sua área.",
            fragment?.view?.findViewById<TextView>(RC.id.txv_description)?.text.toString()
        )
    }

    fun checkIsVisibleInputEmail() {
        assertEquals(
            true,
            fragment?.view?.findViewById<CompEditTextInput>(RL.id.cdt_email)?.isVisible
        )
        val placeHolder =
            (fragment?.view?.findViewById(RL.id.cdt_email) as CompEditTextInput).getPlaceHolder()
        assertEquals("E-mail", placeHolder)
    }

    fun checkIsVisibleInputPassword() {
        assertEquals(
            true,
            fragment?.view?.findViewById<CompEditTextInput>(RL.id.cdt_password)?.isVisible
        )
        val placeHolder =
            (fragment?.view?.findViewById(RL.id.cdt_password) as CompEditTextInput).getPlaceHolder()
        assertEquals("Senha", placeHolder)
    }

    fun checkIsVisibleSwitchButton() {
        assertEquals(
            true,
            fragment?.view?.findViewById<TextView>(RL.id.txv_keep_logged)?.isVisible
        )

        assertEquals(
            "continuar conectado",
            fragment?.view?.findViewById<TextView>(RL.id.txv_keep_logged)?.text.toString()
        )

        assertEquals(true, fragment?.view?.findViewById<SwitchMaterial>(RL.id.swt_keep_logged)?.isVisible)
    }

    fun checkIsVisibleButton() {
        assertEquals(true, fragment?.view?.findViewById<CompPrimeButton>(RL.id.btn_signin)?.isVisible)
        assertEquals(
            "Entrar",
            fragment?.view?.findViewById<TextView>(RC.id.cpb_title)?.text.toString()
        )
    }
}
