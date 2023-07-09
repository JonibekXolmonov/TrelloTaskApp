package uz.realsoft.task.domain.use_case.impl

import uz.realsoft.task.data.mapper.Mapper
import uz.realsoft.task.data.model.model.UserModel
import uz.realsoft.task.data.model.request.LoginRequest
import uz.realsoft.task.domain.repository.AuthRepository
import uz.realsoft.task.domain.use_case.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : LoginUseCase {
    override suspend fun invoke(loginRequest: LoginRequest): Result<UserModel> {

        return try {
            val response = authRepository.login(loginRequest)
            val userModel = Mapper.loginToUserModel(response)
            Result.success(userModel)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}