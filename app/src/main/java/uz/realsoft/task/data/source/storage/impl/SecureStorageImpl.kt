package uz.realsoft.task.data.source.storage.impl

import uz.realsoft.task.common.SharedPref
import uz.realsoft.task.domain.datasource.storage.SecureStorage
import javax.inject.Inject

class SecureStorageImpl @Inject constructor(private val sharedPref: SharedPref) : SecureStorage {

    override fun saveToken(token: String) {
        sharedPref.token = token
    }

    override fun getToke(): String = sharedPref.token
}