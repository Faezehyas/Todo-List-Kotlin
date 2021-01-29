package com.example.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.todolistapp.api.RetrofitClient
import com.example.todolistapp.model.RegisterResponse
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        register_button.setOnClickListener { register_button ->
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


            RetrofitClient.instance.createUser(email,name,username,password)
                .enqueue(object : Callback<RegisterResponse>{
                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                        Toast.makeText(context, response.body()?.token, Toast.LENGTH_SHORT).show()
                        if (response.isSuccessful){
                            register_button.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
        }


    }
}