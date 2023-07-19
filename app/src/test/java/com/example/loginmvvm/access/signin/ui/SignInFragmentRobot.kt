package com.example.loginmvvm.access.signin.ui

import android.widget.TextView
import androidx.core.view.isVisible
import com.compdesign.compbutton.CompPrimeButton
import com.example.loginmvvm.R
import com.compdesign.compedittext.CompEditTextInput
import org.junit.Assert
import org.junit.Ignore

class SignInFragmentRobot {

    private var fragment: SignInFragment? = null

    fun start(fragment: SignInFragment, func: (robot: SignInFragmentRobot) -> Unit) {
        this.fragment = fragment
        func.invoke(this)
    }

    fun checkIsVisibleTitle() {
        Assert.assertEquals(true, fragment?.view?.findViewById<TextView>(R.id.txv_title)?.isVisible)
        Assert.assertEquals(
            "Acessar",
            fragment?.view?.findViewById<TextView>(R.id.txv_title)?.text.toString()
        )
    }

    fun checkIsVisibleSubTitle() {
        Assert.assertEquals(
            true,
            fragment?.view?.findViewById<TextView>(R.id.txv_subtitle)?.isVisible
        )
        Assert.assertEquals(
            "Seja bem vindo!",
            fragment?.view?.findViewById<TextView>(R.id.txv_subtitle)?.text.toString()
        )
    }

    fun checkIsVisibleDescription() {
//        Assert.assertEquals(
//            true,
//            fragment?.view?.findViewById<TextView>(R.id.txv_description)?.isVisible
//        )
//        Assert.assertEquals(
//            "Informe seu e-mail e senha para acessar a sua área.",
//            fragment?.view?.findViewById<TextView>(R.id.txv_description)?.text.toString()
//        )
    }

    fun checkIsVisibleInputEmail() {
        Assert.assertEquals(
            true,
            fragment?.view?.findViewById<CompEditTextInput>(R.id.cdt_email)?.isVisible
        )
        val placeHolder =
            (fragment?.view?.findViewById(R.id.cdt_email) as CompEditTextInput).getPlaceHolder()
        Assert.assertEquals("E-mail", placeHolder)
    }

    fun checkIsVisibleInputPassword() {
        Assert.assertEquals(
            true,
            fragment?.view?.findViewById<CompEditTextInput>(R.id.cdt_password)?.isVisible
        )
        val placeHolder =
            (fragment?.view?.findViewById(R.id.cdt_password) as CompEditTextInput).getPlaceHolder()
        Assert.assertEquals("Senha", placeHolder)
    }

    fun checkIsVisibleButton() {
        Assert.assertEquals(true, fragment?.view?.findViewById<CompPrimeButton>(R.id.btn_signin)?.isVisible)
//        Assert.assertEquals(
//            "Entrar",
//            fragment?.view?.findViewById<TextView>(R.id.cpb_title)?.text.toString()
//        )
    }
}
