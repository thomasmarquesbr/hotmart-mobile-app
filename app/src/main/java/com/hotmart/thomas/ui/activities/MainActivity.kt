package com.hotmart.thomas.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hotmart.thomas.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_HotmartMobileApp)
        setContentView(R.layout.activity_main)
    }
}