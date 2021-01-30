package com.example.todolistapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.todolistapp.api.ApiClient
import com.example.todolistapp.databinding.FragmentLoginBinding
import com.example.todolistapp.model.LoginRequest
import com.example.todolistapp.model.LoginResponse
import com.example.todolistapp.storage.SessionManager
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG1 = "LoginFragment"


class LoginFragment : Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

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

            apiClient = ApiClient()
            sessionManager = SessionManager(requireContext())

            apiClient.getApiService().login(LoginRequest(username, password)).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()
                    if (loginResponse?.toString()?.toInt() == 200 || loginResponse?.token != null) {
                        sessionManager.saveAuthToken(loginResponse.token)
                    } else {
                        Log.e(TAG1, "onResponse: "+loginResponse.toString(),null )
                        Log.e(TAG1, "onResponse: $response",null )
                        Toast.makeText(context, "Error logging in :(", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }


        binding.forgotPasswordTextview.setOnClickListener {
            Toast.makeText(context, "Sorry for you :(", Toast.LENGTH_SHORT).show()
        }


        binding.signupTextview.setOnClickListener {
            it.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment2())
        }

        return binding.root
    }
}