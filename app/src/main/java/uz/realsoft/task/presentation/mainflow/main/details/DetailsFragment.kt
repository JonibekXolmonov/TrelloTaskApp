package uz.realsoft.task.presentation.mainflow.main.details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.realsoft.task.common.Constants.TASK_MODEL
import uz.realsoft.task.common.UiStateList
import uz.realsoft.task.common.UiStateObject
import uz.realsoft.task.common.toast
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.databinding.FragmentDetailsBinding

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var taskModel: TaskModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskModel = arguments.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it?.getParcelable(TASK_MODEL, TaskModel::class.java)!!
            } else {
                it?.get(TASK_MODEL) as TaskModel
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.taskState.collectLatest { state ->
                    when (state) {
                        UiStateObject.EMPTY -> {}

                        UiStateObject.LOADING -> {}

                        is UiStateObject.SUCCESS -> {
                            taskModel = state.data
                            setStatus()
                            toast("Updated successfully")
                        }
                        is UiStateObject.ERROR -> {
                            toast(state.message)
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding.apply {
            tvTaskProject.text = taskModel.projectName
            tvTaskName.text = "Task name: " + taskModel.name
            tvStartDate.text = "Start date : " + taskModel.taskDate
            tvEndDate.text = "End date: " + taskModel.termDate
            tvPriority.text = "Priority: " + taskModel.priority
            setStatus()

            tvStatus.setOnClickListener {
                openStatusChangeDialog()
            }
        }
    }

    private fun setStatus() {
        binding.tvStatus.text = "Status: " + taskModel.status
    }

    private fun openStatusChangeDialog() {
        StatusChangeDialog { status ->
            viewModel.changeStatus(taskModel, status)
        }.show(childFragmentManager, "status change")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}