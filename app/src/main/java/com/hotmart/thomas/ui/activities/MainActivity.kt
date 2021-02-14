package com.hotmart.thomas.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.models.presentation.ResultState
import com.hotmart.thomas.R
import com.hotmart.thomas.ui.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_HotmartMobileApp)
        setContentView(R.layout.activity_main)

        viewModel.locationsLiveData.observe(this, { state ->
            when (state) {
                is ResultState.Success -> setupScreenForSuccessGetLocations(state.data)
                is ResultState.Loading -> setupScreenForLoadingGetLocations()
                is ResultState.Error -> setupScreenForErrorGetLocations(state.errorMessage)
            }
        })
        viewModel.getLocations()
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
}