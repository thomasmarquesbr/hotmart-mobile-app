package com.hotmart.thomas.ui.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel() {

    var disposables = CompositeDisposable()

    fun unbound() {
        disposables.clear()
    }

}