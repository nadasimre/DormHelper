package com.example.dormhelper.screens.menu

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dormhelper.R
import com.example.dormhelper.database.UserDatabase
import com.example.dormhelper.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var viewModel: MenuViewModel
    private lateinit var viewModelFactory: MenuViewFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        val binding : FragmentMenuBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_menu,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDatabaseDao

        val user = arguments?.let { MenuFragmentArgs.fromBundle(it).user }

        viewModelFactory = MenuViewFactory(user!!, dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]

        binding.menuViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = PresetAdapter(PresetAdapter.PresetListener { id ->
            viewModel.onPresetClicked(id)
        })
        binding.presetList.adapter = adapter

        viewModel.navigateToPreset.observe(viewLifecycleOwner, { preset ->
            preset?.let {
                this.findNavController().navigate(
                    MenuFragmentDirections
                        .actionMenuFragmentToPresetDetailFragment(user, preset))
                viewModel.onPresetNavigated()
            }
        })

        viewModel.presetList.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToPresetBuilder.observe(viewLifecycleOwner, { preset ->
            preset?.let {
                this.findNavController().navigate(
                    MenuFragmentDirections
                        .actionMenuFragmentToDormPresetFragment(user, preset.id))
                viewModel.navigatedToBuilder()
            }
        })

        binding.profileManager.setOnClickListener { view: View
            -> view.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToManageUserFragment(user))
        }

        viewModel.showDialog.observe(viewLifecycleOwner, { dialog ->
            if (dialog) {
                val builder = AlertDialog.Builder(requireActivity() as AppCompatActivity)
                builder.setMessage("Are you sure you want to delete all presets?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        viewModel.clearPresets()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
                viewModel.cleared()
            }
        })

        val manager = GridLayoutManager(activity, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) =  when (position) {
                0 -> 3
                else -> 1
            }
        }
        binding.presetList.layoutManager = manager

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}