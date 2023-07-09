package uz.realsoft.task.data.source.cache.impl

import uz.realsoft.task.data.db.TaskDao
import uz.realsoft.task.domain.datasource.cache.CacheDataSource
import javax.inject.Inject

class CacheDataSourceImpl @Inject constructor(private val taskDao: TaskDao) : CacheDataSource {

}