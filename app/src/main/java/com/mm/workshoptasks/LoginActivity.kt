package com.mm.workshoptasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var counter = 0
    private val speaker: Speaker = AndroidSpeaker(this)
    private val prefRepo = PrefRepo(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkIfNotLogged()
        speaker.init()
        showNumOpened()

        passwordView.setOnEditorActionListener { _, actionId, event ->
            val isActionLogin = (actionId == EditorInfo.IME_ACTION_GO ||
                    event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            if (isActionLogin) {
                login()
            }
            isActionLogin
        }

        logoutButton.setOnClickListener {
            login()
        }
    }

    private fun checkIfNotLogged() {
        val emailInPreferences = prefRepo.getEmail()
        if (emailInPreferences != null) {
            loginSuccess(emailInPreferences)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speaker.onDestroy()
    }

    private fun showNumOpened() {
        val numOpened = prefRepo.getEntryCount() + 1
        toast("Opened num $numOpened")
        prefRepo.setEntryCount(numOpened)
    }

    private fun login() {
        val email = emailView.text.toString()
        val pass = passwordView.text.toString()
        if ("@" !in email || pass != "aaa") { // Replace with actual login verification
            counter++
            attemptsLabelView.visibility = View.VISIBLE
            attemptsLabelView.text = getString(R.string.attempts_text, counter)
        } else {
            counter = 0
            attemptsLabelView.visibility = View.GONE
            loginSuccess(email)
        }
    }

    private fun loginSuccess(email: String) {
        prefRepo.setEmail(email)
        MainActivity.start(this, email)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.message_task -> {
            saySomething()
            true
        }
        R.id.send_message_task -> {
            onSendMessageTaskClicked()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun saySomething() {
        speaker.speak("I say something random!")
    }

    private fun onSendMessageTaskClicked() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Some text")
            type = "text/plain"
        }.let { Intent.createChooser(it, "Share using:") }

        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }
}