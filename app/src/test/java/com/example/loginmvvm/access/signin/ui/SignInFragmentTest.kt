package com.example.loginmvvm.access.signin.ui

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.loginmvvm.R
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    @Test
    fun `given open screen then show components`() {
        val scenario =
            launchFragmentInContainer<SignInFragment>(themeResId = R.style.AppTheme_NoActionBar)
        scenario.onFragment { fragment ->
            Assert.assertEquals(
                true,
                fragment.view?.findViewById<TextView>(com.compdesign.R.id.txv_title)?.isVisible
            )
            Assert.assertEquals(
                "Acessar",
                fragment.view?.findViewById<TextView>(com.compdesign.R.id.txv_title)?.text.toString()
            )
        }
    }
}
