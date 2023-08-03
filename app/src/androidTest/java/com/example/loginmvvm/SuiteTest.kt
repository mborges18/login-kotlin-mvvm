package com.example.loginmvvm

import com.example.loginmvvm.access.signin.SignInErrorTest
import com.example.loginmvvm.access.signin.SignInFailureTest
import com.example.loginmvvm.access.signin.SignInInvalidDataTest
import com.example.loginmvvm.access.signin.SignInNotFoundTest
import com.example.loginmvvm.access.signin.SignInSuccessTest
import com.example.loginmvvm.access.signup.SignUpToSignInTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    SignUpToSignInTest::class,
    SignInSuccessTest::class,
    SignInNotFoundTest::class,
    SignInErrorTest::class,
    SignInFailureTest::class,
    SignInInvalidDataTest::class
)
class SuiteTest