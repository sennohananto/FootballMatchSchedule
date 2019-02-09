package com.sennohananto.footballmatchschedule

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.gsonparserfactory.GsonParserFactory
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit



class FootballMatchSchedule: Application() {
    companion object {
        fun getUniqueIMEIId(context: Context): String {
            try {
                val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return ""
                }
                val imei = telephonyManager.deviceId
                Log.e("imei", "=" + imei!!)
                return if (imei != null && !imei.isEmpty()) {
                    imei
                } else {
                    android.os.Build.SERIAL
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "not_found"
        }
    }


    override fun onCreate() {
        super.onCreate()

        val okHttpClient = OkHttpClient().newBuilder()
                .addNetworkInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                    message -> Log.d("API", message)
                })
                .setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
        AndroidNetworking.setParserFactory(GsonParserFactory(Gson()))
        AndroidNetworking.initialize(this, okHttpClient)

        // OneSignal Initialization
//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();
    }


}