package com.hotmart.remoterepository.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiServiceFactory {

    private lateinit var retrofit: Retrofit

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun makeGson() = GsonBuilder()
        .setLenient()
        .create()

    private fun makeRetrofit(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String) = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun makeService(isDebug: Boolean, baseUrl: String): HotmartApiService {
        if (!ApiServiceFactory::retrofit.isInitialized) {
            val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor(isDebug))
            retrofit = makeRetrofit(okHttpClient, makeGson(), baseUrl)
        }
        return retrofit.create(HotmartApiService::class.java)
    }

}