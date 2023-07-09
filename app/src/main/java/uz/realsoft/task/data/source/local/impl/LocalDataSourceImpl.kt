package uz.realsoft.task.data.source.local.impl

import uz.realsoft.task.data.db.TaskDao
import uz.realsoft.task.data.model.entity.TaskEntity
import uz.realsoft.task.domain.datasource.local.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val taskDao: TaskDao): LocalDataSource {
    override suspend fun getAllTasks(): List<TaskEntity> {
        return taskDao.getAllTasks()
    }

    override suspend fun saveTasksToDb(tasks: List<TaskEntity>) {
        taskDao.saveTasks(tasks)
    }

    override suspend fun updateTask(taskEntity: TaskEntity) {
        return taskDao.updateTask(taskEntity)
    }

    override suspend fun updateStatus(id: Int, status: String) {
        taskDao.updateStatus(id, status)
    }
}