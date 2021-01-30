package com.example.todolistapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.model.Todo
import kotlinx.android.synthetic.main.todo_list_item.view.*


class TodoAdapter(private val todos : MutableList<Todo>) :
        RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false))
    }

    private fun toggleStrikeThrough(todoTextView : TextView, isChecked : Boolean){
        if (isChecked){
            todoTextView.paintFlags = todoTextView.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            todoTextView.paintFlags = todoTextView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo (todo : Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            todo_textview.text = currentTodo.title
            todo_checkBox.isChecked = currentTodo.isChecked
            toggleStrikeThrough(todo_textview, currentTodo.isChecked)
            todo_checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todo_textview, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
            todo_delete_button.setOnClickListener {
                todos.removeAt(position)
                notifyItemRemoved(position)
                //notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}