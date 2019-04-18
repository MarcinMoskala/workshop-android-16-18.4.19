package com.mm.workshoptasks.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mm.workshoptasks.R
import com.mm.workshoptasks.presentation.photo.PhotoFragment
import com.mm.workshoptasks.presentation.settings.SettingsFragment
import com.mm.workshoptasks.presentation.argString
import com.mm.workshoptasks.presentation.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val email by argString(EMAIL_ARG)
    private val presenter by lazy { MainPresenter(this, email) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        showFragment(PhotoFragment())
        presenter.onStart()
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun setupNavigation() {
        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    showFragment(PhotoFragment())
                    true
                }
                R.id.navigation_dashboard -> {
                    showFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        private const val EMAIL_ARG = "EMAIL_ARG"

        fun start(activity: Activity, email: String) {
            val intent = Intent(activity, MainActivity::class.java).apply {
                putExtra(EMAIL_ARG, email)
            }
            activity.startActivity(intent)
        }
    }
}
