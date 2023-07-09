package uz.realsoft.task.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.realsoft.task.data.model.model.TaskModel
import uz.realsoft.task.databinding.TaskDescriptionLayoutBinding

class TaskDescriptionAdapter : ListAdapter<TaskModel, TaskDescriptionAdapter.VH>(DiffUtil()) {

    lateinit var onTaskClick: (TaskModel,) -> Unit

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<TaskModel>() {
        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }
    }

    class VH(val binding: TaskDescriptionLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(
            TaskDescriptionLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            tvTaskDescription.text = getItem(position).name

            root.setOnClickListener {
                onTaskClick(getItem(position))
            }
        }
    }
}