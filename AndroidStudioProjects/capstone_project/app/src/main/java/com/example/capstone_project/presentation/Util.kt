package com.example.capstone_project.presentation

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
}
