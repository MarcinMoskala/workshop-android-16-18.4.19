package com.mm.workshoptasks.presentation.main

class MainPresenter(
    private val view: MainView,
    private val email: String
) {
    fun onStart() {
        view.showMessage("Hello, $email")
    }
}