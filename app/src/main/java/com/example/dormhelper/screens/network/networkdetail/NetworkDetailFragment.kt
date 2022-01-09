package com.example.dormhelper.screens.network.networkdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.dormhelper.databinding.FragmentNetworkDetailBinding

class NetworkDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(activity).application
        val binding = FragmentNetworkDetailBinding.inflate(inflater)

        val dorm = NetworkDetailFragmentArgs.fromBundle(requireArguments()).dorm

        val viewModelFactory = NetworkDetailFactory(dorm, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory)[NetworkDetailViewModel::class.java]


        binding.lifecycleOwner = this
        return binding.root
    }
}