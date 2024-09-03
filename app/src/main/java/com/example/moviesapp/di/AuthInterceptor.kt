package com.example.moviesapp.di

import com.example.moviesapp.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =chain.request()
            .newBuilder()
            .addHeader("Authorization","Bearer $TOKEN")
            .addHeader("accept","application/json")
            .build()
        return chain.proceed(request)
    }
}