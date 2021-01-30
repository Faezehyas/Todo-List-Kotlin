package com.example.todolistapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.TodoListItemBinding
import com.example.todolistapp.model.TodoResponse

class TodoAdapterGet : RecyclerView.Adapter<TodoAdapterGet.TodoViewHolder>() {

    class TodoViewHolder(val binding : TodoListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<TodoResponse>(){
        override fun areItemsTheSame(oldItem: TodoResponse, newItem: TodoResponse): Boolean {
            return oldItem._id == newItem._id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: TodoResponse, newItem: TodoResponse): Boolean {
            return oldItem == newItem
        }
    }

    private var differ = AsyncListDiffer(this, diffCallBack)
    var todos : List<TodoResponse>
    get() = differ.currentList
    set(value) { differ.submitList(value) }


    override fun getItemCount() = todos.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ))
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            todoTextview.text = todo.title
            todoCheckBox.isChecked = todo.completed

        }
    }
}