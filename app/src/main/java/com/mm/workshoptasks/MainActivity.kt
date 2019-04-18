package com.mm.workshoptasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val email by argString(EMAIL_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(PhotoFragment())
        setupNavigation()
        toast("Hello, $email")
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
