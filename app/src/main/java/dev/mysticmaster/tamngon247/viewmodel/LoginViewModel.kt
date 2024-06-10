package dev.mysticmaster.tamngon247.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mysticmaster.tamngon247.feature.config.RetrofitConfig
import dev.mysticmaster.tamngon247.feature.data.model.AdminModel
import dev.mysticmaster.tamngon247.feature.data.model.CustomerModel
import dev.mysticmaster.tamngon247.feature.data.request.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _isAdminAuthenticated = MutableLiveData<Long?>()
    val isAdminAuthenticated: LiveData<Long?> = _isAdminAuthenticated

    private val _isCustomerAuthenticated = MutableLiveData<Long?>()
    val isCustomerAuthenticated: LiveData<Long?> = _isCustomerAuthenticated

    private val _customer = MutableLiveData<CustomerModel?>()
    val customer: LiveData<CustomerModel?> = _customer

    private val _admin = MutableLiveData<AdminModel?>()
    val admin: LiveData<AdminModel?> = _admin

    fun login(username: String, password: String, rememberMe: Boolean, typeLogin: Boolean) {
        viewModelScope.launch {
            if (typeLogin) {
                val response =
                    RetrofitConfig().customerService.postCustomerLogin(
                        LoginRequest(
                            username = username,
                            password = password
                        )
                    )
                if (response.code() == 200) {
                    _isCustomerAuthenticated.value = 200
                    _customer.value = response.body()?.toCustomerModel()
                } else if (response.code() == 404) {
                    _isCustomerAuthenticated.value = 404
                    _customer.value = null
                } else if (response.code() == 400) {
                    _isCustomerAuthenticated.value = 400
                    _customer.value = null
                }
            } else {
                val response = RetrofitConfig().adminService.postAdminLogin(
                    LoginRequest(
                        username = username,
                        password = password
                    )
                )
                if (response.code() == 200) {
                    _isAdminAuthenticated.value = 200
                    _admin.value = response.body()?.toAdminModel()
                } else if (response.code() == 404) {
                    _isAdminAuthenticated.value = 404
                    _admin.value = null
                } else if (response.code() == 400) {
                    _isAdminAuthenticated.value = 400
                    _admin.value = null
                }
            }
        }
    }

    fun resetAdminAuthenticationState() {
        _isAdminAuthenticated.value = null
    }

    fun resetCustomerAuthenticationState() {
        _isCustomerAuthenticated.value = null
    }
}