package com.hotmart.domain.usecases.base

import io.reactivex.Scheduler 


interface PostExecutionThread { 

    val scheduler: Scheduler 

}