package com.mm.workshoptasks.presentation.settings

import com.mm.workshoptasks.data.StorageRepo

class SettingsPresenter(
    private val view: SettingsView,
    private val storageRepo: StorageRepo
) {

    fun onLogoutClicked() {
        storageRepo.removeEmail()
        view.backToLogin()
    }
}