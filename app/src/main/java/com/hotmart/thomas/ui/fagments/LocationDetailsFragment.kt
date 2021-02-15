package com.hotmart.thomas.ui.fagments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hotmart.domain.models.presentation.Location
import com.hotmart.domain.models.presentation.LocationDetails
import com.hotmart.domain.models.presentation.ResultState
import com.hotmart.domain.models.presentation.Schedule
import com.hotmart.thomas.R
import com.hotmart.thomas.databinding.FragmentLocationDetailsBinding
import com.hotmart.thomas.ui.activities.MainActivity
import com.hotmart.thomas.ui.adapters.PhotosAdapter
import com.hotmart.thomas.ui.adapters.ReviewsAdapter
import com.hotmart.thomas.ui.extensions.navigateWithAnimations
import com.hotmart.thomas.ui.extensions.showError
import com.hotmart.thomas.ui.viewmodels.MainViewModel
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel


class LocationDetailsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private val args: LocationDetailsFragmentArgs by navArgs()
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding get() = _binding!!
    private val mActivity by lazy { activity as MainActivity }
    private lateinit var location: Location
    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var reviewsAdapter: ReviewsAdapter

    /** LifeCycle **/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        parseArguments()
        initializeViewModelObservers()
        initializeViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.unbound()
    }

    /** ActionBar **/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_location_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId) {
            android.R.id.home -> {
                val action = LocationDetailsFragmentDirections.actionLocationDetailsFragmentToTab1()
                findNavController().navigateWithAnimations(action, true)
                true
            }
            R.id.action_share -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    /** locationDetailsLiveData **/

    private fun setupScreenForSuccessGetLocationDetails(data: LocationDetails?) {
        data?.let {
            binding.pbLoading.visibility = View.GONE
            showAllViews()
            setDetails(it)
        } ?: run { setupScreenForErrorGetLocationDetails(getString(R.string.unexpected_error_get_location_details)) }
    }

    private fun setupScreenForLoadingGetLocationDetails() {
        hideAllViews()
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun setupScreenForErrorGetLocationDetails(errorMessage: String?) {
        binding.pbLoading.visibility = View.GONE
        Snackbar.make(
            requireView(),
            errorMessage ?: getString(R.string.unexpected_error_get_location_details),
            Snackbar.LENGTH_LONG
        ).showError()
    }

    /** Functions **/

    private fun parseArguments() {
        location = args.location
    }

    private fun initializeViewModelObservers() {
        viewModel.locationDetailsLiveData.observe(this, { state ->
            when (state) {
                is ResultState.Success -> setupScreenForSuccessGetLocationDetails(state.data)
                is ResultState.Loading -> setupScreenForLoadingGetLocationDetails()
                is ResultState.Error -> setupScreenForErrorGetLocationDetails(state.errorMessage)
            }
        })
    }

    private fun initializeViews() {
        photosAdapter = PhotosAdapter(requireContext())
        binding.rvPhotos.adapter = photosAdapter
        binding.rvPhotos.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
        reviewsAdapter = ReviewsAdapter(requireContext())
        binding.rvReviews.adapter = reviewsAdapter
        binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
        mActivity.run {
            showHomeButton()
            title = location.name
            expandActionBar()
            setImage(location.getImageUrl())
        }
        viewModel.getDetails(location)
    }

    private fun setDetails(locationDetails: LocationDetails) {
        binding.run {
            tvAboutContent.text = locationDetails.about
            tvPhone.text = locationDetails.phone
            tvAddress.text = locationDetails.adress
        }
        setSchedule(locationDetails.schedule)
        photosAdapter.photos = locationDetails.images
        reviewsAdapter.reviews = locationDetails.reviews
    }

    private fun setSchedule(schedule: List<Schedule>) {

    }

    private fun showAllViews() {
        binding.run {
            tvAbout.visibility = View.VISIBLE
            tvAboutContent.visibility = View.VISIBLE
            tvAddress.visibility = View.VISIBLE
            tvPhone.visibility = View.VISIBLE
            tvPhotos.visibility = View.VISIBLE
            tvReviews.visibility = View.VISIBLE
            tvSchedule.visibility = View.VISIBLE
            tvSeeMore.visibility = View.VISIBLE
            ivAddress.visibility = View.VISIBLE
            ivClock.visibility = View.VISIBLE
            ivPhone.visibility = View.VISIBLE
            rvPhotos.visibility = View.VISIBLE
            rvReviews.visibility = View.VISIBLE
        }
    }

    private fun hideAllViews() {
        binding.run {
            tvAbout.visibility = View.GONE
            tvAboutContent.visibility = View.GONE
            tvAddress.visibility = View.GONE
            tvPhone.visibility = View.GONE
            tvPhotos.visibility = View.GONE
            tvReviews.visibility = View.GONE
            tvSchedule.visibility = View.GONE
            tvSeeMore.visibility = View.GONE
            ivAddress.visibility = View.GONE
            ivClock.visibility = View.GONE
            ivPhone.visibility = View.GONE
            rvPhotos.visibility = View.GONE
            rvReviews.visibility = View.GONE
        }
    }

}