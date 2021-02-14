package com.hotmart.thomas.ui.fagments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.models.presentation.ResultState
import com.hotmart.thomas.R
import com.hotmart.thomas.databinding.FragmentHomeBinding
import com.hotmart.thomas.ui.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment: Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.unbound()
    }

    /** locationsLiveData **/

    private fun setupScreenForSuccessGetLocations(data: List<Location>?) {
        data?.let {

        } ?: setupScreenForErrorGetLocations(getString(R.string.unexpected_error_get_locations))
    }

    private fun setupScreenForLoadingGetLocations() {

    }

    private fun setupScreenForErrorGetLocations(errorMessage: String?) {

    }

    private fun initializeViews() {
        viewModel.getLocations()
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