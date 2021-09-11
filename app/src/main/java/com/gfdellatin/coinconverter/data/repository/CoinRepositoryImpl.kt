package com.gfdellatin.coinconverter.data.repository

import com.gfdellatin.coinconverter.data.services.AwesomeService
import kotlinx.coroutines.flow.flow

class CoinRepositoryImpl(
    private val service: AwesomeService
) : CoinRepository {
    override suspend fun getExchangedValue(coins: String) = flow {
        val exchangeValue = service.exchangeValue(coins)
        val exchange = exchangeValue.values.first()
        emit(exchange)
    }
}