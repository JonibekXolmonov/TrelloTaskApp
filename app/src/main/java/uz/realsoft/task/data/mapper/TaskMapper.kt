package uz.realsoft.task.data.mapper

import uz.realsoft.task.data.model.entity.TaskEntity
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.data.model.model.UserModel
import uz.realsoft.task.data.model.response.LoginResponse
import uz.realsoft.task.data.model.response.TaskRemote

object Mapper {

    fun taskEntityToTaskModel(value: TaskEntity) = TaskModel(
        name = value.name,
        taskID = value.taskID,
        index = value.index,
        projectID = value.projectID,
        projectName = value.projectName,
        ownerID = value.ownerID,
        ownerName = value.ownerName,
        ownerAvatar = value.ownerAvatar,
        executorID = value.executorID,
        executorName = value.executorName,
        executorAvatar = value.executorAvatar,
        taskDate = value.taskDate,
        termDate = value.termDate,
        priority = value.priority,
        status = value.status
    )

    fun taskModelToTaskEntity(value: TaskModel) = TaskEntity(
        name = value.name,
        taskID = value.taskID,
        index = value.index,
        projectID = value.projectID,
        projectName = value.projectName,
        ownerID = value.ownerID,
        ownerName = value.ownerName,
        ownerAvatar = value.ownerAvatar,
        executorID = value.executorID,
        executorName = value.executorName,
        executorAvatar = value.executorAvatar,
        taskDate = value.taskDate,
        termDate = value.termDate,
        priority = value.priority,
        status = value.status
    )

    fun taskRemoteToTaskModel(value: TaskRemote) = TaskModel(
        name = value.name,
        taskID = value.taskID,
        index = value.index,
        projectID = value.projectID,
        projectName = value.projectName,
        ownerID = value.ownerID,
        ownerName = value.ownerName,
        ownerAvatar = value.ownerAvatar,
        executorID = value.executorID,
        executorName = value.executorName,
        executorAvatar = value.executorAvatar,
        taskDate = value.taskDate,
        termDate = value.termDate,
        priority = value.priority,
        status = value.status
    )

    fun taskToEntity(value: TaskRemote) = TaskEntity(
        name = value.name,
        taskID = value.taskID,
        index = value.index,
        projectID = value.projectID,
        projectName = value.projectName,
        ownerID = value.ownerID,
        ownerName = value.ownerName,
        ownerAvatar = value.ownerAvatar,
        executorID = value.executorID,
        executorName = value.executorName,
        executorAvatar = value.executorAvatar,
        taskDate = value.taskDate,
        termDate = value.termDate,
        priority = value.priority,
        status = value.status
    )

    fun loginToUserModel(value: LoginResponse): UserModel = UserModel(token = value.token)
}