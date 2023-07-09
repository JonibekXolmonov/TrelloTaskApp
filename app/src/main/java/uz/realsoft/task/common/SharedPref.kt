package uz.realsoft.task.common

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.realsoft.task.common.Constants.EMPTY_STRING
import uz.realsoft.task.common.Constants.TOKEN
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext val context: Context) {

    private val pref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

    var token: String
        get() = pref.getString(TOKEN, EMPTY_STRING)!!
        set(value) = pref.edit { this.putString(TOKEN, value) }
}