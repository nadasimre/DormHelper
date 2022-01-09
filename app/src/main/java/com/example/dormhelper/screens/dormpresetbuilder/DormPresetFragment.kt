package com.example.dormhelper.screens.dormpresetbuilder

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
import com.example.dormhelper.databinding.FragmentDormPresetBinding

class DormPresetFragment : Fragment() {

    private lateinit var viewModel: DormPresetViewModel
    private lateinit var viewModelFactory: DormPresetFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val binding : FragmentDormPresetBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dorm_preset,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val user = arguments?.let { DormPresetFragmentArgs.fromBundle(it).user }

        val arguments = DormPresetFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = DormPresetFactory(arguments.presetId, dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory)[DormPresetViewModel::class.java]

        binding.dormPresetViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToMenu.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController().navigate(
                    DormPresetFragmentDirections.actionDormPresetFragmentToMenuFragment(user!!))
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }
}