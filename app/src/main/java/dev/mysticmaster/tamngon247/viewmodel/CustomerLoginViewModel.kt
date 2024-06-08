package dev.mysticmaster.tamngon247.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CustomerLoginViewModel {
    private val _isGoToAdminLogin = MutableLiveData<Boolean?>()
    val isGoToAdminLogin: LiveData<Boolean?> = _isGoToAdminLogin

    fun goToAdminLogin(){
        _isGoToAdminLogin.value = true
    }
}