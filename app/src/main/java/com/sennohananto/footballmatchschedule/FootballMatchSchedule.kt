package com.sennohananto.footballmatchschedule

import android.app.Application
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.gsonparserfactory.GsonParserFactory
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.google.gson.Gson
import com.onesignal.OneSignal
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class FootballMatchSchedule: Application() {
    override fun onCreate() {
        super.onCreate()

        val okHttpClient = OkHttpClient().newBuilder()
                .addNetworkInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("API", message) })
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
        AndroidNetworking.setParserFactory(GsonParserFactory(Gson()))
        AndroidNetworking.initialize(this, okHttpClient)

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}