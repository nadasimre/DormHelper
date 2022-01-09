package com.example.dormhelper.screens.registration

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
import com.example.dormhelper.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var viewModelFactory: RegistrationViewFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val binding : FragmentRegistrationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_registration,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        viewModelFactory = RegistrationViewFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]

        binding.registrationViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.cancelClicked.observe(viewLifecycleOwner, { cancel ->
                if(cancel){
                    findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
                    viewModel.navigateLoginDone()
                }
            })

        return binding.root
    }
}