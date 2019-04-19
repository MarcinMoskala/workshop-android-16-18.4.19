package com.mm.workshoptasks.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mm.workshoptasks.data.AndroidSpeaker
import com.mm.workshoptasks.R
import com.mm.workshoptasks.data.StorageRepoImpl
import com.mm.workshoptasks.databinding.ActivityLoginBinding
import com.mm.workshoptasks.presentation.main.MainActivity
import com.mm.workshoptasks.presentation.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val vm by lazy { LoginViewModel(this, StorageRepoImpl(this), AndroidSpeaker(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
            .vm = vm

        vm.onStart()

        passwordView.setOnEditorActionListener { _, actionId, event ->
            val isActionLogin = (actionId == EditorInfo.IME_ACTION_GO ||
                    event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            if (isActionLogin) {
                vm.onLoginClicked()
            }
            isActionLogin
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.message_task -> {
            vm.onMessageTaskClicked()
            true
        }
        R.id.send_message_task -> {
            vm.onSendTaskClicked()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun startShareMessageActivity(message: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }.let { Intent.createChooser(it, "Share using:") }

        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun switchToMainActivity(email: String) {
        MainActivity.start(this, email)
        finish()
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }
}