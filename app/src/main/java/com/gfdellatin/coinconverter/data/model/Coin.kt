package com.gfdellatin.coinconverter.data.model

import java.util.*

enum class Coin(val locale: Locale) {
    USD(Locale.US),
    EUR(Locale.UK),
    BRL(Locale("pt", "BR")),
    BTC(Locale.US);

    companion object {
        fun getByName(name: String) = values().find { it.name == name} ?: BRL
    }
}