package uz.realsoft.task.data.model.response

import com.google.gson.annotations.SerializedName

data class TaskRemote(
    @SerializedName("task_id")
    val taskID: Long,

    val index: Long,

    @SerializedName("project_id")
    val projectID: String,

    @SerializedName("project_name")
    val projectName: String,

    @SerializedName("owner_id")
    val ownerID: Long,

    @SerializedName("owner_name")
    val ownerName: String,

    @SerializedName("owner_avatar")
    val ownerAvatar: String,

    @SerializedName("executor_id")
    val executorID: Long,

    @SerializedName("executor_name")
    val executorName: String,

    @SerializedName("executor_avatar")
    val executorAvatar: String,

    @SerializedName("task_date")
    val taskDate: String,

    @SerializedName("term_date")
    val termDate: String,

    val name: String,
    val priority: String,
    val status: String
)
