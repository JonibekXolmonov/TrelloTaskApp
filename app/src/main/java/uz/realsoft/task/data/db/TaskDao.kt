package uz.realsoft.task.data.db

import androidx.room.*
import uz.realsoft.task.data.model.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTasks(tasks: List<TaskEntity>)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Query("UPDATE tasks SET status=:status WHERE taskId = :id")
    suspend fun updateStatus(id: Int, status: String)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertTask(task: TaskEntity)

//    @Query("SELECT * FROM tasks ORDER BY index")
//    suspend fun getAllTasks(): List<TaskEntity>

}