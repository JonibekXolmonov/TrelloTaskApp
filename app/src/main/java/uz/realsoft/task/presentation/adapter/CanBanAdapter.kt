package uz.realsoft.task.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.realsoft.task.common.Constants.DONE
import uz.realsoft.task.common.Constants.IN_PROGRESS
import uz.realsoft.task.common.Constants.IN_REVIEW
import uz.realsoft.task.common.Constants.NEW
import uz.realsoft.task.common.Constants.STATUS_DONE
import uz.realsoft.task.common.Constants.STATUS_IN_PROGRESS
import uz.realsoft.task.common.Constants.STATUS_IN_REVIEW
import uz.realsoft.task.common.Constants.STATUS_NEW
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.databinding.ItemCanbanLayoutBinding
import java.util.*


class CanBanAdapter : ListAdapter<TaskModel, ViewHolder>(DiffUtil()) {

    lateinit var onTaskClick: (TaskModel) -> Unit
    lateinit var onDrag: (TaskModel, TaskModel) -> Unit
    private var context: Context? = null


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<TaskModel>() {
        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class NewVH(private val binding: ItemCanbanLayoutBinding) : ViewHolder(binding.root) {
        fun bind(tasks: List<TaskModel>) {
            binding.tvStatus.text = STATUS_NEW
            refreshTaskDescriptionAdapter(tasks = tasks, rvTask = binding.rvTask)
        }
    }

    inner class InProgressVH(private val binding: ItemCanbanLayoutBinding) :
        ViewHolder(binding.root) {
        fun bind(tasks: List<TaskModel>) {
            binding.tvStatus.text = STATUS_IN_PROGRESS
            refreshTaskDescriptionAdapter(tasks = tasks, rvTask = binding.rvTask)
        }
    }

    inner class InReviewVH(private val binding: ItemCanbanLayoutBinding) :
        ViewHolder(binding.root) {
        fun bind(tasks: List<TaskModel>) {
            binding.tvStatus.text = STATUS_IN_REVIEW
            refreshTaskDescriptionAdapter(tasks = tasks, rvTask = binding.rvTask)
        }
    }

    inner class DoneVH(private val binding: ItemCanbanLayoutBinding) : ViewHolder(binding.root) {
        fun bind(tasks: List<TaskModel>) {
            binding.tvStatus.text = STATUS_DONE
            refreshTaskDescriptionAdapter(tasks = tasks, rvTask = binding.rvTask)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            1 -> IN_PROGRESS
            2 -> IN_REVIEW
            3 -> DONE
            else -> NEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        return when (viewType) {
            IN_PROGRESS ->
                InProgressVH(
                    ItemCanbanLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            IN_REVIEW ->
                InReviewVH(
                    ItemCanbanLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            DONE ->
                DoneVH(
                    ItemCanbanLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> NewVH(
                ItemCanbanLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is NewVH -> {
                holder.bind(currentList.filter { it.status == STATUS_NEW }.sortedBy { it.index })
            }
            is InProgressVH -> {
                holder.bind(currentList.filter { it.status == STATUS_IN_PROGRESS }
                    .sortedBy { it.index })
            }
            is InReviewVH -> {
                holder.bind(currentList.filter { it.status == STATUS_IN_REVIEW }
                    .sortedBy { it.index })

            }
            is DoneVH -> {
                holder.bind(currentList.filter { it.status == STATUS_DONE }.sortedBy { it.index })
            }
        }
    }

    // returns 4 because of 4 types of status (new,progress,review,done)
    override fun getItemCount(): Int {
        return 4
    }

    private fun refreshTaskDescriptionAdapter(tasks: List<TaskModel>, rvTask: RecyclerView) {
        rvTask.adapter = TaskDescriptionAdapter().apply {
            submitList(tasks)

            onTaskClick = { taskModel ->
                this@CanBanAdapter.onTaskClick(taskModel)
            }
        }

        //dragging rv items
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rvTask.addItemDecoration(dividerItemDecoration)

        val simpleCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
                0
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder,
                    target: ViewHolder
                ): Boolean {
                    val fromPosition = viewHolder.adapterPosition
                    val toPosition = target.adapterPosition
                    Collections.swap(tasks, fromPosition, toPosition)

                    //exchange index
                    val fromPositionIndex = tasks[fromPosition].index
                    tasks[fromPosition].index = tasks[toPosition].index
                    tasks[toPosition].index = fromPositionIndex

                    onDrag(tasks[fromPosition], tasks[toPosition])

                    recyclerView.adapter!!.notifyItemMoved(fromPosition, toPosition)
                    return false
                }

                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {}
            }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(rvTask)
    }
}