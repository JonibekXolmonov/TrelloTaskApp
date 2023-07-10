package uz.realsoft.task.presentation.authflow.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.realsoft.task.common.UiStateObject
import uz.realsoft.task.data.model.request.LoginRequest
import uz.realsoft.task.domain.use_case.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<UiStateObject<Boolean>>(UiStateObject.EMPTY)
    val loginState get() = _loginState.asStateFlow()

    fun login(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {
            _loginState.value = UiStateObject.ERROR(message = "Email or password is empty")
        } else {
            _loginState.value = UiStateObject.LOADING

            viewModelScope.launch {
                val request = LoginRequest(login = "jhonhanks@gmail.com", password = "aA1234567")
                loginUseCase(request)
                    .onSuccess {
                        _loginState.value = UiStateObject.SUCCESS(true)
                    }
                    .onFailure {
                        _loginState.value = UiStateObject.ERROR(
                            message = it.localizedMessage ?: "Something went wrong"
                        )
                    }
                return@launch
            }
        }
    }
}