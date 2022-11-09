package com.example.capstone_project.presentation.util


import com.example.capstone_project.R

object Util {

    fun getResources(book: String): Int {
        return when (book) {
            "btc_mxn" -> {
                R.drawable.ic_bitcoin
            }
            "eth_mxn" -> {
                R.drawable.ic_eth
            }
            "xrp_mxn" -> {
                R.drawable.ic_xrp
            }
            "ltc_mxn" -> {
                R.drawable.ic_ltc
            }
            "bch_mxn" -> {
                R.drawable.ic_bch
            }
            "tusd_mxn" -> {
                R.drawable.ic_tusd
            }
            "mana_mxn" -> {
                R.drawable.ic_mana
            }
            "bat_mxn" -> {
                R.drawable.ic_bat
            }
            "dai_mxn" -> {
                R.drawable.ic_dai
            }
            "usd_mxn" -> {
                R.drawable.ic_usd
            }
            else -> {
                0
            }
        }
    }

    fun isNetworkEnabled(): Boolean {
        var isWiFiConnect = true
        var isMobileConnect = true
        /*instance?.let {
            val manager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            manager.allNetworks.forEach { network ->
                manager.getNetworkInfo(network)?.apply {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        isWiFiConnect = isWiFiConnect or isConnected
                    }
                    if (type == ConnectivityManager.TYPE_MOBILE) {
                        isMobileConnect = isMobileConnect or isConnected
                    }
                }
            }
        }*/

        return isWiFiConnect || isMobileConnect
    }
}
