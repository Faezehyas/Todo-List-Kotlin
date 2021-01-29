package com.example.todolistapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.todolistapp.api.RetrofitClient
import com.example.todolistapp.databinding.FragmentLoginBinding
import com.example.todolistapp.model.LoginResponse
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)


        binding.loginButton.setOnClickListener { v: View ->

            val username = binding.loginUsernameInput.text.toString().trim()
            val password = binding.loginPasswordInput.text.toString().trim()

            if (username.isEmpty()){
                login_username_input.error = "username required"
                login_username_input.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                login_password_input.error = "password required"
                login_password_input.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(username, password)
                .enqueue(object : Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        Toast.makeText(context, response.body()?.token, Toast.LENGTH_SHORT).show()
                        if (response.isSuccessful){
                            Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show()
                            v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListOfItemsFragment2())
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
        }


        binding.loginButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListOfItemsFragment2())
        }


        binding.forgotPasswordTextview.setOnClickListener {
            Toast.makeText(context, "Sorry :(", Toast.LENGTH_SHORT).show()
        }


        binding.signupTextview.setOnClickListener {
            it.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment2())
        }

        //setHasOptionsMenu(true)
        return binding.root
    }
}