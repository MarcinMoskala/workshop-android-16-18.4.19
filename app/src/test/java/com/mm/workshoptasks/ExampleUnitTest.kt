package com.mm.workshoptasks

import com.mm.workshoptasks.data.StorageRepo
import com.mm.workshoptasks.presentation.login.LoginView
import com.mm.workshoptasks.presentation.login.LoginViewModel
import io.mockk.*
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun `For incorrect email, error label is displayed`() {
        val view: LoginView = mockk(relaxed = true)
        val storageRepo = mockk<StorageRepo>(relaxed = true)
        every { storageRepo.getEntryCount() } returns 0
        every { storageRepo.getEmail() } returns null
        val vm = LoginViewModel(view, storageRepo, mockk(relaxed = true))

        vm.onStart()
        vm.emailValue.set("incorrect")
        vm.passValue.set("incorrect")
        vm.onLoginClicked()

        assertEquals(true, vm.attemptsLabelVisibility.get())
        assertTrue("1" in vm.attemptsLabelText.get()!!)

        vm.onLoginClicked()
        assertTrue("2" in vm.attemptsLabelText.get()!!)
    }

    @Test
    fun `For correct email and pass, error label is hidden and we move to main`() {
        val view: LoginView = mockk(relaxed = true)
        val storageRepo = mockk<StorageRepo>(relaxed = true)
        every { storageRepo.getEntryCount() } returns 0
        every { storageRepo.getEmail() } returns null
        val vm = LoginViewModel(view, storageRepo, mockk(relaxed = true))

        vm.onStart()
        val correctEmail = "marcinmoskala@gmail.com"
        val correctPass = "aaa"
        vm.emailValue.set(correctEmail)
        vm.passValue.set(correctPass)
        vm.onLoginClicked()

        assertEquals(false, vm.attemptsLabelVisibility.get())
        assertEquals("", vm.attemptsLabelText.get()!!)

        verify {
            storageRepo.setEmail(correctEmail)
            view.switchToMainActivity(correctEmail)
        }
    }

    @Test
    fun `When email in storage, move to another window`() {
        val view: LoginView = mockk(relaxed = true)
        val storageRepo = mockk<StorageRepo>(relaxed = true)
        val email = "SOME EMAIL"
        every { storageRepo.getEmail() } returns email
        every { storageRepo.getEntryCount() } returns 0
        val vm = LoginViewModel(view, storageRepo, mockk(relaxed = true))

        vm.onStart()

        verify { view.switchToMainActivity(email) }
    }
}
