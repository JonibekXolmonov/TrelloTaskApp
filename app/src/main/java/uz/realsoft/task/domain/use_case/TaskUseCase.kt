package uz.realsoft.task.domain.use_case

import uz.realsoft.task.data.model.model.TaskModel

interface TaskUseCase {

    suspend operator fun invoke(): Result<List<TaskModel>>

    suspend fun updateTask(taskModel: TaskModel): Result<Boolean>

    suspend fun updateStatus(taskModel: TaskModel, statusCode: Int): Result<TaskModel>

}