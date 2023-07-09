package uz.realsoft.task.data.repository

import uz.realsoft.task.data.mapper.Mapper
import uz.realsoft.task.data.model.entity.TaskEntity
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.data.model.response.TaskRemote
import uz.realsoft.task.domain.datasource.local.LocalDataSource
import uz.realsoft.task.domain.datasource.remote.RemoteDataSource
import uz.realsoft.task.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MainRepository {

    override suspend fun getAllTask(): List<TaskRemote> {
        return remoteDataSource.getAllTask()
    }

    override suspend fun getAllTaskFromDb(): List<TaskEntity> {
        return localDataSource.getAllTasks()
    }

    override suspend fun saveTasksToDb(tasks: List<TaskModel>) {
        localDataSource.saveTasksToDb(tasks.map { takRemote ->
            Mapper.taskModelToTaskEntity(takRemote)
        })
    }

    override suspend fun updateTask(taskEntity: TaskEntity) {
        localDataSource.updateTask(taskEntity)
    }

    override suspend fun updateStatus(id: Int, status: String) {
        localDataSource.updateStatus(id,status)
    }
}