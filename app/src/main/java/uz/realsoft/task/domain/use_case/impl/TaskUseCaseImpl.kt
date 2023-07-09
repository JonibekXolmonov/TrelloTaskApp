package uz.realsoft.task.domain.use_case.impl

import uz.realsoft.task.common.Constants.DONE
import uz.realsoft.task.common.Constants.IN_PROGRESS
import uz.realsoft.task.common.Constants.IN_REVIEW
import uz.realsoft.task.common.Constants.NEW
import uz.realsoft.task.common.Constants.STATUS_DONE
import uz.realsoft.task.common.Constants.STATUS_IN_PROGRESS
import uz.realsoft.task.common.Constants.STATUS_IN_REVIEW
import uz.realsoft.task.common.Constants.STATUS_NEW
import uz.realsoft.task.data.mapper.Mapper
import uz.realsoft.task.data.model.entity.TaskEntity
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.domain.repository.MainRepository
import uz.realsoft.task.domain.use_case.TaskUseCase
import javax.inject.Inject

class TaskUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
) :
    TaskUseCase {

    override suspend fun invoke(): Result<List<TaskModel>> {
        return try {
            val dbResponse = mainRepository.getAllTaskFromDb()
            if (dbResponse.isNotEmpty()) {
                val taskModels = dbResponse.map { taskEntity ->
                    Mapper.taskEntityToTaskModel(taskEntity)
                }
                Result.success(taskModels)
            } else {
                getTasksFromApi()
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    private suspend fun getTasksFromApi(): Result<List<TaskModel>> {
        return try {
            val response = mainRepository.getAllTask()
            val taskModels = response.map { taskRemote ->
                Mapper.taskRemoteToTaskModel(taskRemote)
            }
            //save to db
            mainRepository.saveTasksToDb(taskModels)
            Result.success(taskModels)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun updateTask(taskModel: TaskModel): Result<Boolean> {
        return try {
            val request = Mapper.taskModelToTaskEntity(taskModel)
            val response = mainRepository.updateTask(request)

            Result.success(true)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun updateStatus(taskModel: TaskModel, statusCode: Int): Result<TaskModel> {
        return if (taskModel.status == getStatus(statusCode)) {
            Result.failure(Exception("This operation is impossible"))
        } else {
            try {

                val allTasks = this.invoke()

                allTasks.onSuccess {
                    val sameStatusTasks = it.filter { it.status == getStatus(statusCode) }
                    val maxIndexedTask = sameStatusTasks.maxByOrNull { it.index }
                    if (maxIndexedTask != null)
                        taskModel.index = maxIndexedTask.index + 1
                    else taskModel.index = 1
                    taskModel.status = getStatus(statusCode)
                    mainRepository.updateTask(taskEntity = Mapper.taskModelToTaskEntity(taskModel))
                    taskModel.status = getStatus(statusCode)
                }
                Result.success(taskModel)
            } catch (e: Throwable) {
                Result.failure(e)
            }
        }
    }

    private fun getStatus(statusCode: Int) = when (statusCode) {
        NEW -> STATUS_NEW
        IN_PROGRESS -> STATUS_IN_PROGRESS
        IN_REVIEW -> STATUS_IN_REVIEW
        DONE -> STATUS_DONE
        else -> STATUS_NEW
    }
}