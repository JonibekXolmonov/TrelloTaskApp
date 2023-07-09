package uz.realsoft.task.domain.datasource.remote

import uz.realsoft.task.data.model.request.LoginRequest
import uz.realsoft.task.data.model.response.LoginResponse
import uz.realsoft.task.data.model.response.TaskRemote

interface RemoteDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun getAllTask(): List<TaskRemote>
}