package com.mm.workshoptasks.presentation.login

interface LoginView {
    fun startShareMessageActivity(message: String)
    fun showMessage(message: String)
    fun switchToMainActivity(email: String)
}