package uz.realsoft.task.data.repository

import uz.realsoft.task.data.model.request.LoginRequest
import uz.realsoft.task.data.model.response.LoginResponse
import uz.realsoft.task.domain.datasource.remote.RemoteDataSource
import uz.realsoft.task.domain.datasource.storage.SecureStorage
import uz.realsoft.task.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val secureStorage: SecureStorage
) :
    AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        val response = remoteDataSource.login(loginRequest)
        secureStorage.saveToken(response.token)
        return remoteDataSource.login(loginRequest)
    }
}