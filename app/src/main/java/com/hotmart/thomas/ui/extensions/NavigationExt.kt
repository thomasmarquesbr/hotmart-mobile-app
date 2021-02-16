package com.hotmart.thomas.ui.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.hotmart.thomas.R


private val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_left)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_right)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

private val navOptionsBack = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_right)
    .setPopEnterAnim(R.anim.slide_in_right)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateWithAnimations(destinationId: Int, toBack: Boolean = false) {
    val navOpt = if (toBack) navOptionsBack else navOptions
    this.navigate(destinationId, null, navOpt)
}

fun NavController.navigateWithAnimations(navDirection: NavDirections, toBack: Boolean = false) {
    val navOpt = if (toBack) navOptionsBack else navOptions
    this.navigate(navDirection, navOpt)
}