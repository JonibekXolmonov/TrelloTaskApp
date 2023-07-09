package uz.realsoft.task.presentation.mainflow.main.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.realsoft.task.common.UiStateObject
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.domain.use_case.TaskUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {

    private val _taskState = MutableStateFlow<UiStateObject<TaskModel>>(UiStateObject.EMPTY)
    val taskState get() = _taskState.asStateFlow()

    fun changeStatus(taskModel: TaskModel, status: Int) {
        _taskState.value = UiStateObject.LOADING

        viewModelScope.launch {
            val result = taskUseCase.updateStatus(taskModel, statusCode = status)

            result.onSuccess { tasks ->
                _taskState.value = UiStateObject.SUCCESS(tasks)
            }

            result.onFailure {
                _taskState.value =
                    UiStateObject.ERROR(message = it.localizedMessage ?: "Something went wrong")
            }
            return@launch
        }
    }
}