package uz.realsoft.task.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.realsoft.task.data.model.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun createTaskDao(): TaskDao
}