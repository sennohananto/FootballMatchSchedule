package com.sennohananto.footballmatchschedule

import android.app.ProgressDialog
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

var progressDialog: ProgressDialog? = null

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}

fun toDateIndo(date:String, pattern:String): String? {
    val localindo = Locale("in","ID","ID")
    val inputFormat = SimpleDateFormat(pattern, localindo)
    val outputFormat = SimpleDateFormat("E, dd MMM yyyy", localindo)


    return outputFormat.format(inputFormat.parse(date))
}

fun getLocaleCalendar(date: String,pattern: String): Calendar? {
    val localindo = Locale("in","ID","ID")
    val inputFormat = SimpleDateFormat(pattern, localindo)

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = inputFormat.parse(date).time

    return calendar
}

fun toHourIndo(time: String):String{
    val concatedString = time.subSequence(0,5).toString()

    val sourceFormat = SimpleDateFormat("HH:mm")
    sourceFormat.timeZone = TimeZone.getTimeZone("UTC")
    val parsed = sourceFormat.parse(concatedString) // => Date is in UTC now

    val tz = TimeZone.getTimeZone("Asia/Jakarta")
    val destFormat = SimpleDateFormat("HH:mm")
    destFormat.timeZone = tz

    val result = destFormat.format(parsed)
    return result
}

internal fun FragmentManager.replaceFragment(containerViewId: Int,
                                             fragment: Fragment,
                                             tag: String) {
    this.beginTransaction()
            .disallowAddToBackStack()
            .replace(containerViewId, fragment, tag)
            .commitNow()
}

fun showProgressDialog(context: Context, message:String){
    if(progressDialog==null){
        progressDialog = ProgressDialog(context)
        progressDialog?.setCancelable(false)
    }
    progressDialog?.setMessage(message)
    progressDialog?.show()
}

fun dismissProgressDialog(){
    if(progressDialog!!.isShowing){
        progressDialog?.dismiss()
    }
}
