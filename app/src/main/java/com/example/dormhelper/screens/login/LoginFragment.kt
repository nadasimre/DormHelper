package com.example.dormhelper.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dormhelper.R
import com.example.dormhelper.database.UserDatabase
import com.example.dormhelper.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        viewModelFactory = LoginViewFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.registerClicked.observe(viewLifecycleOwner, { register ->
                if(register){
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
                    viewModel.navigateDone()
                }
            })

        viewModel.user.observe(viewLifecycleOwner, { user ->
            user?.let{
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMenuFragment(user))
            }
        })

        return binding.root
    }

}