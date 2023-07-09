package uz.realsoft.task.data.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskModel(
    val taskID: Long,
    var index: Long,
    val projectID: String,
    val projectName: String,
    val ownerID: Long,
    val ownerName: String,
    val ownerAvatar: String,
    val executorID: Long,
    val executorName: String,
    val executorAvatar: String,
    val taskDate: String,
    val termDate: String,
    val name: String,
    val priority: String,
    var status: String
) : Parcelable