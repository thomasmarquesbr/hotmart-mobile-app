package com.hotmart.thomas.ui.fagments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
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
import com.hotmart.thomas.ui.extensions.setImageResourceFrom
import com.hotmart.thomas.ui.extensions.showError
import com.hotmart.thomas.ui.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.abs
import kotlin.math.roundToInt


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
        viewModel.locationDetailsLiveData.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ResultState.Success -> setupScreenForSuccessGetLocationDetails(state.data)
                is ResultState.Loading -> setupScreenForLoadingGetLocationDetails()
                is ResultState.Error -> setupScreenForErrorGetLocationDetails(state.errorMessage)
            }
        })
    }

    private fun initializeViews() {
        setupActionBar()
        setupPhotosList()
        setupReviewsList()
        viewModel.getDetails(location)
    }

    private fun setupActionBar() {
        mActivity.run {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.run {
                setHomeButtonEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                title = location.name
            }
            title = location.name
        }
        binding.appBar.addOnOffsetChangedListener(AppBarLayout
            .OnOffsetChangedListener { appBarLayout, verticalOffset ->
                val maxScroll = appBarLayout.totalScrollRange.toFloat()
                binding.llToolbar.alpha = 1 - (abs(verticalOffset).toFloat() / maxScroll)
            })
        binding.appCompatImageView.setImageResourceFrom(location.getImageUrl())
    }

    private fun setupReviewsList() {
        reviewsAdapter = ReviewsAdapter(requireContext())
        binding.rvReviews.adapter = reviewsAdapter
        binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupPhotosList() {
        photosAdapter = PhotosAdapter()
        binding.rvPhotos.adapter = photosAdapter
        binding.rvPhotos.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun setDetails(locationDetails: LocationDetails) {
        binding.run {
            tvAboutContent.text = locationDetails.about
            tvPhone.text = locationDetails.phone
            tvAddress.text = locationDetails.adress
            tvSeeMore.text = getString(R.string.see_more_reviews, locationDetails.totalReviews)
        }
        setSchedule(locationDetails.schedule)
        setPhone(locationDetails.phone)
        setReviewValue(locationDetails.review)
        photosAdapter.photos = locationDetails.images
        reviewsAdapter.reviews = locationDetails.reviews
    }

    private fun setPhone(phone: String) {
        val formattedPhone = StringBuilder()
        formattedPhone.append("${phone.substring(0, 3)} ") // Country code
        formattedPhone.append("${phone.substring(3, 5)} ") // DDD
        formattedPhone.append("${phone.substring(5, 10)} ") // part 1
        formattedPhone.append("${phone.substring(10, phone.length)} ") // part2
        binding.tvPhone.text = formattedPhone.toString()
    }

    private fun setSchedule(schedule: List<Schedule>) {
        val schedulesMap = mutableMapOf<String, String>()
        val daysArray = resources.getStringArray(R.array.days)
        schedule.forEach { s ->
            s.convertToMap(schedulesMap, daysArray)
        }
        var schedules = StringBuilder()
        schedulesMap.forEach { (time, days) ->
            if (time != "-") {
                var formatedDays = days.substring(0, days.length - 2)
                if (formatedDays.count { it == ',' } > 3) {
                    val splitedDays = formatedDays.split(",")
                    formatedDays = "${splitedDays.first()} á ${splitedDays.last()}"
                }
                schedules.append("${formatedDays}: ${time.replace("-", " ás ")}\n")
            }
        }
        val scheduleText = if (schedules.length > 2)
            schedules.substring(0, schedules.length - 2)
        else
            schedules.toString()
        binding.tvSchedule.text = scheduleText
    }

    private fun setReviewValue(review: Double) {
        binding.tvReviewValue.text = review.toString()
        when (review.roundToInt()) {
            1 -> {
                binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar2.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar3.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar4.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
            }
            2 -> {
                binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar2.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar3.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar4.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
            }
            3 -> {
                binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar2.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar3.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar4.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
            }
            4 -> {
                binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar2.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar3.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar4.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
            }
            5 -> {
                binding.ivStar1.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar2.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar3.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar4.setBackgroundResource(R.drawable.ic_on)
                binding.ivStar5.setBackgroundResource(R.drawable.ic_on)
            }
            else -> {
                binding.ivStar1.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar2.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar3.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar4.setBackgroundResource(R.drawable.ic_off)
                binding.ivStar5.setBackgroundResource(R.drawable.ic_off)
            }
        }
    }

    private fun showAllViews() {
        binding.run {
            ivStar1.visibility = View.VISIBLE
            ivStar2.visibility = View.VISIBLE
            ivStar3.visibility = View.VISIBLE
            ivStar4.visibility = View.VISIBLE
            ivStar5.visibility = View.VISIBLE
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
            ivStar1.visibility = View.GONE
            ivStar2.visibility = View.GONE
            ivStar3.visibility = View.GONE
            ivStar4.visibility = View.GONE
            ivStar5.visibility = View.GONE
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