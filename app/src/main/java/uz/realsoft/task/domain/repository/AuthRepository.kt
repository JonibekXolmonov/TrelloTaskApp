package uz.realsoft.task.domain.repository

import uz.realsoft.task.data.model.request.LoginRequest
import uz.realsoft.task.data.model.response.LoginResponse

interface AuthRepository {
   suspend fun login(loginRequest: LoginRequest): LoginResponse
}