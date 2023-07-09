package uz.realsoft.task.domain.use_case

import uz.realsoft.task.data.model.model.UserModel
import uz.realsoft.task.data.model.request.LoginRequest

interface LoginUseCase {
    suspend operator fun invoke(loginRequest: LoginRequest): Result<UserModel>
}