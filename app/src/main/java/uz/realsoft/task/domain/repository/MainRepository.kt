package uz.realsoft.task.domain.repository

import uz.realsoft.task.data.model.entity.TaskEntity
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.data.model.response.TaskRemote

interface MainRepository {
    suspend fun getAllTask(): List<TaskRemote>

    suspend fun getAllTaskFromDb(): List<TaskEntity>

    suspend fun saveTasksToDb(tasks: List<TaskModel>)

    suspend fun updateTask(taskEntity: TaskEntity)

    suspend fun updateStatus(id: Int, status: String)
}