package com.hotmart.thomas.ui.fagments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.models.presentation.ResultState
import com.hotmart.thomas.R
import com.hotmart.thomas.databinding.FragmentHomeBinding
import com.hotmart.thomas.ui.activities.MainActivity
import com.hotmart.thomas.ui.adapters.LocationsAdapter
import com.hotmart.thomas.ui.extensions.navigateWithAnimations
import com.hotmart.thomas.ui.extensions.showError
import com.hotmart.thomas.ui.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment: Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mActivity by lazy { activity as MainActivity }
    private lateinit var locationsAdapter: LocationsAdapter

    /** LifeCycle **/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModelObservers()
        initializeViews()
    }

    override fun onStop() {
        super.onStop()
        binding.rvLocations.isNestedScrollingEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.unbound()
    }

    /** locationsLiveData **/

    private fun setupScreenForSuccessGetLocations(data: List<Location>?) {
        data?.let {
            binding.pbLoading.visibility = View.GONE
            binding.rvLocations.visibility = View.VISIBLE
            locationsAdapter.locations = it
        } ?: setupScreenForErrorGetLocations(getString(R.string.unexpected_error_get_locations))
    }

    private fun setupScreenForLoadingGetLocations() {
        binding.pbLoading.visibility = View.VISIBLE
        binding.rvLocations.visibility = View.GONE
    }

    private fun setupScreenForErrorGetLocations(errorMessage: String?) {
        binding.pbLoading.visibility = View.GONE
        binding.rvLocations.visibility = View.VISIBLE
        Snackbar.make(
            requireView(),
            errorMessage ?: getString(R.string.unexpected_error_get_locations),
            Snackbar.LENGTH_LONG
        ).showError()
    }

    /** Functions **/

    private fun initializeViews() {
        setupActionBar()
        setupLocationsList()
        viewModel.getLocations()
    }

    private fun setupActionBar() {
        mActivity.run {
            setSupportActionBar(binding.toolbar)
            title = getString(R.string.home)
        }
    }

    private fun setupLocationsList() {
        locationsAdapter = LocationsAdapter(
            requireContext(),
            onItemClicked = { location ->
                val action = HomeFragmentDirections.actionTab1ToLocationDetailsFragment(location)
                findNavController().navigateWithAnimations(action)
            })
        binding.rvLocations.adapter = locationsAdapter
        binding.rvLocations.layoutManager = StaggeredGridLayoutManager(
            2,
            LinearLayoutManager.VERTICAL
        )
    }

    private fun initializeViewModelObservers() {
        viewModel.locationsLiveData.observe(this, { state ->
            when (state) {
                is ResultState.Success -> setupScreenForSuccessGetLocations(state.data)
                is ResultState.Loading -> setupScreenForLoadingGetLocations()
                is ResultState.Error -> setupScreenForErrorGetLocations(state.errorMessage)
            }
        })
    }

}