package com.hotmart.thomas.ui.fagments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hotmart.domain.models.presentation.Location
import com.hotmart.thomas.R
import com.hotmart.thomas.databinding.FragmentHomeBinding
import com.hotmart.thomas.databinding.FragmentLocationDetailsBinding
import com.hotmart.thomas.ui.activities.MainActivity
import com.hotmart.thomas.ui.extensions.navigateWithAnimations
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

    /** Functions **/

    private fun parseArguments() {
        location = args.location
    }

    private fun initializeViewModelObservers() {

    }

    private fun initializeViews() {
        mActivity.run {
            showHomeButton()
            title = location.name
            expandActionBar()
            setImage(location.getImageUrl())
        }
    }

}