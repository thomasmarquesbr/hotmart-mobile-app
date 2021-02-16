package com.hotmart.thomas.ui.fagments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


abstract class BaseFragment: Fragment() {

    abstract fun fragmentLayoutId(): Int

    abstract fun parseArguments()

    abstract fun initializeViews()

    abstract fun initializeViewModelObservers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(fragmentLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArguments()
        initializeViewModelObservers()
        initializeViews()
    }

}