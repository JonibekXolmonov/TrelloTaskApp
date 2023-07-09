package uz.realsoft.task.data.source.remote.impl

import uz.realsoft.task.data.model.request.LoginRequest
import uz.realsoft.task.data.model.response.LoginResponse
import uz.realsoft.task.data.model.response.TaskRemote
import uz.realsoft.task.data.network.ApiService
import uz.realsoft.task.domain.datasource.remote.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }

    override suspend fun getAllTask(): List<TaskRemote> {
        return apiService.getAllTasks()
    }
}