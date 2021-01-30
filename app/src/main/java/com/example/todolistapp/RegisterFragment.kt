package com.example.todolistapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.todolistapp.api.ApiClient
import com.example.todolistapp.model.RegisterRequest
import com.example.todolistapp.model.RegisterResponse
import com.example.todolistapp.storage.SessionManager
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        register_button.setOnClickListener {
            val email = register_email.text.toString().trim()
            val name = register_name.text.toString().trim()
            val username = register_username.text.toString().trim()
            val password = register_password.text.toString().trim()

            if (email.isEmpty()){
                register_email.error = "Email required"
                register_email.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()){
                register_username.error = "username required"
                register_username.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                register_password.error = "password required"
                register_password.requestFocus()
                return@setOnClickListener
            }

            if (name.isEmpty()){
                register_name.error = "name required"
                register_name.requestFocus()
                return@setOnClickListener
            }


            apiClient = ApiClient()
            sessionManager = SessionManager(requireContext())

            apiClient.getApiService().register(RegisterRequest(username,name,email,password)).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    val registerResponse = response.body()
                    if (registerResponse?.toString()?.toInt() == 400)
                        Toast.makeText(context, "json returned 400 in response", Toast.LENGTH_SHORT).show()
                    if (registerResponse?.toString()?.toInt() == 200 && registerResponse?.token != null) {
                        sessionManager.saveAuthToken(registerResponse.token)
                        Toast.makeText(context, "now go back and login ^_^", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Error signing in :(", Toast.LENGTH_SHORT).show()
                        Log.e("onResponse", ": ${registerResponse?.token}" )
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(context, "json returned 400 in failure", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })


        }
    }
}