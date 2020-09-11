package com.example.gads.Services

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceBuilder{
    private const val URL = "https://gadsapi.herokuapp.com"
    private const val URL_FORM = "https://docs.google.com/forms/d/e/"

    private val logger= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val headerInterceptor = object: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .addHeader("Accept-Language", Locale.getDefault().language)
                .build()
            val response = chain.proceed(request)
            return response
        }

    }

    private val okHttp = OkHttpClient.Builder().addInterceptor(logger).addInterceptor(headerInterceptor)


    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
    private val form_builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()
    private val retrofitForm = form_builder.build()


    fun<T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
    fun<T> buildServiceForm(serviceType: Class<T>): T {
        return retrofitForm.create(serviceType)
    }
}