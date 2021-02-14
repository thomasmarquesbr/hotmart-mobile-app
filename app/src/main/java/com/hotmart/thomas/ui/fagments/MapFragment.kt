package com.hotmart.thomas.ui.fagments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hotmart.thomas.databinding.FragmentHomeBinding
import com.hotmart.thomas.databinding.FragmentMapBinding
import com.hotmart.thomas.ui.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MapFragment: Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    /** LifeCycle **/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}