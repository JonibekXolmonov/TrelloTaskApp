package uz.realsoft.task.common

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(msg: String = "") {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}