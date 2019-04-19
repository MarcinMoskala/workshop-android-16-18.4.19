package com.mm.workshoptasks.presentation.login

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mm.workshoptasks.R
import com.mm.workshoptasks.data.Speaker
import com.mm.workshoptasks.data.StorageRepo
import kotlinx.android.synthetic.main.activity_login.*

class LoginViewModel(
    private val view: LoginView,
    private val storageRepo: StorageRepo,
    private val speaker: Speaker
): ViewModel() {
    val emailValue = ObservableField<String>("")
    val passValue = ObservableField<String>("")
    val attemptsLabelText = ObservableField<String>("")
    val attemptsLabelVisibility = ObservableBoolean(false)

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
        val email = emailValue.get() ?: ""
        val pass = passValue.get() ?: ""
        if ("@" !in email || pass != "aaa") { // Replace with actual login verification
            counter++
            attemptsLabelVisibility.set(true)
            attemptsLabelText.set("Failed to login num $counter")
        } else {
            counter = 0
            attemptsLabelVisibility.set(false)
            attemptsLabelText.set("")

            storageRepo.setEmail(email)
            loginSuccess(email)
        }
    }

    private fun loginSuccess(email: String) {
        view.switchToMainActivity(email)
    }
}