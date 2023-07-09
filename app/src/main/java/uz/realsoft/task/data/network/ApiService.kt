package uz.realsoft.task.data.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.realsoft.task.data.model.request.LoginRequest
import uz.realsoft.task.data.model.response.LoginResponse
import uz.realsoft.task.data.model.response.TaskRemote

interface ApiService {

    @POST("api/v2/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("api/v1/task/get_all_tasks")
    suspend fun getAllTasks(): List<TaskRemote>

}