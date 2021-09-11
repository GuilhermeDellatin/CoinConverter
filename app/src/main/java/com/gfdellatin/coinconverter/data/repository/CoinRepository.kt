package com.gfdellatin.coinconverter.data.repository

import com.gfdellatin.coinconverter.data.model.ExchangeResponseValue
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun  getExchangedValue(coins: String): Flow<ExchangeResponseValue>
}