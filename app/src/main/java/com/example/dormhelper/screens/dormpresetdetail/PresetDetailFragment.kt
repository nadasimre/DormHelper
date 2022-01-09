package com.example.dormhelper.screens.dormpresetdetail

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
import com.example.dormhelper.databinding.FragmentPresetDetailBinding

class PresetDetailFragment : Fragment() {

    private lateinit var viewModel: PresetDetailViewModel
    private lateinit var viewModelFactory: PresetDetailFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val binding : FragmentPresetDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_preset_detail,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val user = arguments?.let { PresetDetailFragmentArgs.fromBundle(it).user }
        val preset = arguments?.let { PresetDetailFragmentArgs.fromBundle(it).presetForImage }

        viewModelFactory = PresetDetailFactory(preset!!, dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory)[PresetDetailViewModel::class.java]

        binding.presetDetailViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.onDeletePreset.observe(viewLifecycleOwner, { delete ->
            if (delete) {
                findNavController().navigate(
                    PresetDetailFragmentDirections.actionPresetDetailFragmentToMenuFragment(user!!))
                viewModel.afterDeleteNavigated()
            }
        })

        return binding.root
    }

}