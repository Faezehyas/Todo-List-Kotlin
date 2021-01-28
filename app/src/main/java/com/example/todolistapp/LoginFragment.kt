package com.example.todolistapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.todolistapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false)
        binding.loginButton.setOnClickListener { v: View ->
            v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListOfItemsFragment2())
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

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }*/
}