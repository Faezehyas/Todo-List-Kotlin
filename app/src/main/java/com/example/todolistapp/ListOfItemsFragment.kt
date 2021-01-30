package com.example.todolistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.api.RetrofitInstance
import com.example.todolistapp.databinding.FragmentListOfItemsBinding
import kotlinx.android.synthetic.main.fragment_list_of_items.*
import retrofit2.HttpException
import java.io.IOException

const val TAG2 = "ListOfItemsFragment"

class ListOfItemsFragment : Fragment() {

    private lateinit var binding : FragmentListOfItemsBinding

    private lateinit var todoAdapterGet : TodoAdapterGet


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.fragment_list_of_items , container, false)

        binding = FragmentListOfItemsBinding.inflate(layoutInflater)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException) {
                Log.e(TAG2, "IOException, you might not have internet connection", null)
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG2, "HttpException, unexpected response", null)
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                todoAdapterGet.todos = response.body()!!
            } else {
                Log.e(TAG2, "Response not successful", null)
            }

        }

        return (binding.root)
    }

    fun setupRecyclerView() = binding.recyclerview.apply {
        todoAdapterGet = TodoAdapterGet()
        adapter = todoAdapterGet
        layoutManager = LinearLayoutManager(this@ListOfItemsFragment.context)

    }





    /*
        private lateinit var todoAdapter : TodoAdapter


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

        */

}