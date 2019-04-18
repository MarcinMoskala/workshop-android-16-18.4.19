package com.mm.workshoptasks.presentation.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mm.workshoptasks.data.StorageRepo
import com.mm.workshoptasks.R
import com.mm.workshoptasks.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    private val prefRepo by lazy { StorageRepo(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutButton.setOnClickListener { logout() }
    }

    private fun logout() {
        prefRepo.removeEmail()
        val activity = activity!!
        LoginActivity.start(activity)
        activity.finish()
    }
}
