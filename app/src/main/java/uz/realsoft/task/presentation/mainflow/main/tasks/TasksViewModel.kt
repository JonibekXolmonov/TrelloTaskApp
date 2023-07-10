package uz.realsoft.task.presentation.mainflow.main.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.realsoft.task.common.UiStateList
import uz.realsoft.task.common.UiStateObject
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.domain.use_case.TaskUseCase
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {

    private val _taskState = MutableStateFlow<UiStateList<TaskModel>>(UiStateList.EMPTY)
    val taskState get() = _taskState.asStateFlow()

    private val _taskUpdateState = MutableStateFlow<UiStateObject<Boolean>>(UiStateObject.EMPTY)
    val taskUpdateState get() = _taskUpdateState.asStateFlow()

    fun getAllTasks() {

        _taskState.value = UiStateList.LOADING

        viewModelScope.launch {
            val result = taskUseCase()

            result.onSuccess { tasks ->
                _taskState.value = UiStateList.SUCCESS(tasks)
            }

            result.onFailure {
                _taskState.value =
                    UiStateList.ERROR(message = it.localizedMessage ?: "Something went wrong")
            }

            return@launch
        }
    }

    fun updateTaskModel(taskModel: TaskModel) {
        _taskUpdateState.value = UiStateObject.LOADING
        viewModelScope.launch {
            val result = taskUseCase.updateTask(taskModel)

            result.onSuccess {
                _taskUpdateState.value = UiStateObject.SUCCESS(it)
            }

            result.onFailure {
                _taskUpdateState.value =
                    UiStateObject.ERROR(message = it.localizedMessage ?: "Something went wrong")
            }
        }
    }
}