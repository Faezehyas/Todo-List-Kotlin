package com.example.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_of_items.*

class ListOfItemsFragment : Fragment() {

    private lateinit var todoAdapter : TodoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_of_items , container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoAdapter = TodoAdapter(mutableListOf())
        recyclerview.adapter = todoAdapter

        recyclerview.layoutManager = LinearLayoutManager(activity)

        todo_add_button.setOnClickListener {
            val todoTitle = todo_add_edittext.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                todo_add_edittext.text.clear()
            }
        }

        /*
        try delete in here instead of TodoAdapter
        todo_delete_button.setOnClickListener {}
        */
    }
}