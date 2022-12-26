package com.grandtask.redditnews.data.manager

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat

interface InternetConnectionManagerInterface {
    val isConnectedToInternet: Boolean
}

class InternetConnectionManager(private val context: Context) :
    InternetConnectionManagerInterface {

    override val isConnectedToInternet: Boolean
        get() {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.activeNetworkInfo

            return networkInfo != null && networkInfo.isConnected
        }
}

fun parseData(){
    val dateFromServer = "20/10/2020"
    val split = dateFromServer.split(" ")
    val time = split[1]
    val date = split[0]
    val parsedDate = SimpleDateFormat("dd/MM/yyyy hh:mm").parse(date)
    val timeFormat = SimpleDateFormat("hh:mm")
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    timeFormat.format(parsedDate)
    dateFormat.format(parsedDate)

}