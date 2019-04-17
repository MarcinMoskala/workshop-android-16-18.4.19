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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var counter = 0
    private val speaker = AndroidSpeaker()
    private val prefRepo = PrefRepo(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(prefRepo.getEmail() != null) {
            startSecond()
        }

        speaker.init(this)

        showNumOpened()

        passwordView.setOnEditorActionListener { _, actionId, event ->
            val isActionLogin = (actionId == EditorInfo.IME_ACTION_GO ||
                    event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            if (isActionLogin) {
                login()
            }
            isActionLogin
        }

        loginButton.setOnClickListener {
            login()
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
        if ("@" !in email || pass != "aaa") {
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
        startSecond()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.add_task -> {
            startSecond()
            true
        }
        R.id.message_task -> {
            onMessageTaskClicked()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun startSecond() {
        SecondActivity.start(this, "THIS IS SOME TEXT", User("Marcin", "Moskała"))
        finish()
    }

    private fun onMessageTaskClicked() {
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
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}