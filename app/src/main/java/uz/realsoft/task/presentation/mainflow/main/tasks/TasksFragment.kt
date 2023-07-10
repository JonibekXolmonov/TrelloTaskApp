package uz.realsoft.task.presentation.mainflow.main.tasks

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.core.os.BuildCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.realsoft.task.R
import uz.realsoft.task.common.Constants.TASK_MODEL
import uz.realsoft.task.common.UiStateList
import uz.realsoft.task.common.UiStateObject
import uz.realsoft.task.common.provideImage
import uz.realsoft.task.common.toast
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.databinding.FragmentTasksBinding
import uz.realsoft.task.presentation.adapter.CanBanAdapter


@AndroidEntryPoint
class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<TasksViewModel>()
    private val adapter by lazy { CanBanAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getAllTasks()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObserver()
        initUpdateObserver()
        onBackPressed()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.taskState.collectLatest { state ->
                    when (state) {
                        UiStateList.EMPTY -> {}

                        UiStateList.LOADING -> {}

                        is UiStateList.SUCCESS -> {
                            refreshAdapter(state.data)
                        }
                        is UiStateList.ERROR -> {
                            toast(msg = state.message)
                        }
                    }
                }
            }
        }
    }

    private fun initUpdateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.taskUpdateState.collectLatest { state ->
                    when (state) {
                        UiStateObject.EMPTY -> {}

                        UiStateObject.LOADING -> {}

                        is UiStateObject.SUCCESS -> {

                        }
                        is UiStateObject.ERROR -> {
                            toast("Could not change index")
                        }
                    }
                }
            }
        }
    }

    private fun refreshAdapter(tasks: List<TaskModel>) {
        adapter.submitList(tasks)
        binding.rvCanBan.adapter = adapter
    }

    private fun initViews() {
        Picasso.get().load(provideImage().random()).into(binding.ivBackground)

        adapter.onTaskClick = { taskModel ->
            findNavController().navigate(
                R.id.action_tasksFragment_to_detailsFragment,
                bundleOf(TASK_MODEL to taskModel)
            )
        }

        adapter.onDrag = { fromTaskModel: TaskModel, toTaskModel: TaskModel ->
            viewModel.updateTaskModel(fromTaskModel)
            viewModel.updateTaskModel(toTaskModel)
        }

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCanBan)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}