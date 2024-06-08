package dev.mysticmaster.tamngon247.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AdminLoginViewModel {
    private val _isGoToCustomerLogin = MutableLiveData<Boolean?>()
    val isGoToCustomerLogin: LiveData<Boolean?> = _isGoToCustomerLogin

    fun goToCustomerLogin(){
        _isGoToCustomerLogin.value = true
    }
}