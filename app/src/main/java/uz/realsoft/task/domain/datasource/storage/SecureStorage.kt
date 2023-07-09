package uz.realsoft.task.domain.datasource.storage

interface SecureStorage {

    fun saveToken(token: String)

    fun getToke(): String
}