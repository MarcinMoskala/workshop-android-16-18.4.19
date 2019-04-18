package com.mm.workshoptasks.presentation.login

import com.mm.workshoptasks.data.Speaker
import com.mm.workshoptasks.data.StorageRepo

class LoginPresenter(
    private val view: LoginView,
    private val storageRepo: StorageRepo,
    private val speaker: Speaker
) {
    private var counter = 0

    fun onStart() {
        checkIfNotLogged()
        speaker.init()
        showNumOpened()
    }

    fun onDestroy() {
        speaker.onDestroy()
    }

    fun onMessageTaskClicked() {
        speaker.speak("I say something random!")
    }

    fun onSendTaskClicked() {
        view.startShareMessageActivity("Some text")
    }

    private fun checkIfNotLogged() {
        val emailInPreferences = storageRepo.getEmail()
        if (emailInPreferences != null) {
            loginSuccess(emailInPreferences)
        }
    }

    private fun showNumOpened() {
        val numOpened = storageRepo.getEntryCount() + 1
        view.showMessage("Opened num $numOpened")
        storageRepo.setEntryCount(numOpened)
    }

    fun onLoginClicked() {
        val email = view.getEmail()
        val pass = view.getPassword()
        if ("@" !in email || pass != "aaa") { // Replace with actual login verification
            counter++
            view.displayAttempts(counter)
        } else {
            counter = 0
            view.displayAttempts(null)
            loginSuccess(email)
        }
    }

    private fun loginSuccess(email: String) {
        storageRepo.setEmail(email)
        view.switchToMainActivity(email)
    }
}