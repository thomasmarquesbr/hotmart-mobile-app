package com.hotmart.thomas.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.hotmart.thomas.R
import com.hotmart.thomas.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_HotmartMobileApp)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeViews() {
        setupActionBar()
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_sale) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label.toString()
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        collapseActionBar()
        title = ""
        supportActionBar?.run {
            setHomeButtonEnabled(false)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    fun expandActionBar() {
        binding.appBar.setExpanded(true)
    }

    fun collapseActionBar() {
        binding.appBar.setExpanded(false)
    }

    fun showHomeButton() {
        supportActionBar?.run {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    fun hideHomeButton() {
        supportActionBar?.run {
            setHomeButtonEnabled(false)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    fun setImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .error(R.drawable.ic_broken_image)
            .into(binding.appCompatImageView)
    }


}