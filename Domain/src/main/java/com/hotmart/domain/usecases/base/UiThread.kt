package com.hotmart.domain.usecases.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


class UiThread : PostExecutionThread {

    override val scheduler: Scheduler 
        get() = AndroidSchedulers.mainThread() 

}