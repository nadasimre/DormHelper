package com.example.dormhelper.screens.manageuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dormhelper.R
import com.example.dormhelper.database.UserDatabase
import com.example.dormhelper.databinding.FragmentManageUserBinding

class ManageUserFragment : Fragment() {

    private lateinit var viewModel: ManageUserViewModel
    private lateinit var viewModelFactory: ManageUserFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        val binding : FragmentManageUserBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_manage_user,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val user = arguments?.let { ManageUserFragmentArgs.fromBundle(it).user }

        viewModelFactory = ManageUserFactory(user!!, dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory)[ManageUserViewModel::class.java]

        binding.manageUserViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.onDeleteUser.observe(viewLifecycleOwner, { delete ->
            if(delete){
                findNavController().navigate(ManageUserFragmentDirections.actionManageUserFragmentToLoginFragment())
                viewModel.afterDeleteNavigated()
            }
        })

        binding.Cancel.setOnClickListener { view: View
            -> view.findNavController().navigate(ManageUserFragmentDirections.actionManageUserFragmentToMenuFragment(user))
        }

        return binding.root
    }
}