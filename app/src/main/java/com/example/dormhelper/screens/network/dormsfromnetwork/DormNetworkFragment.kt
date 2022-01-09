package com.example.dormhelper.screens.network.dormsfromnetwork

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dormhelper.R
import com.example.dormhelper.databinding.FragmentDormNetworkBinding

class DormNetworkFragment : Fragment() {

    private val viewModel: DormNetworkViewModel by lazy {
        ViewModelProvider(this)[DormNetworkViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val binding : FragmentDormNetworkBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dorm_network,
            container,
            false
        )

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.dormsNetwork.adapter = DormAdapter(DormAdapter.DormListener {
            viewModel.displayDormDetails(it)
        })

        val manager = LinearLayoutManager(activity)
        binding.dormsNetwork.layoutManager = manager

        viewModel.navigateToSelectedDorm.observe(viewLifecycleOwner, {
            if ( null != it ) {
                this.findNavController().navigate(
                    DormNetworkFragmentDirections.actionDormNetworkFragmentToNetworkDetailFragment(it))
                viewModel.displayDormDetailsComplete()
            }
        })

        return binding.root
    }
}