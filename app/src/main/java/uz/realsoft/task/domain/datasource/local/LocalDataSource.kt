package uz.realsoft.task.domain.datasource.local

import uz.realsoft.task.data.model.entity.TaskEntity

interface LocalDataSource {

    suspend fun getAllTasks(): List<TaskEntity>
    suspend fun saveTasksToDb(tasks: List<TaskEntity>)
    suspend fun updateTask(taskEntity: TaskEntity)
    suspend fun updateStatus(id: Int, status: String)
}