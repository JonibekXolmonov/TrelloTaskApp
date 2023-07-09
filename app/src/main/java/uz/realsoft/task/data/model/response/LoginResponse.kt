package uz.realsoft.task.data.model.response

import com.google.gson.annotations.SerializedName

typealias LoginResponse = LoginPayLoad

data class LoginPayLoad(
    val token: String,
    val user: User
)

data class User(
    @SerializedName("avatar")
    val profileImage: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("user_id")
    val userId: Int
)