package com.gfdellatin.coinconverter.data.repository

import com.gfdellatin.coinconverter.core.extensions.exceptions.RemoteException
import com.gfdellatin.coinconverter.data.model.ErrorResponse
import com.gfdellatin.coinconverter.data.services.AwesomeService
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CoinRepositoryImpl(
    private val service: AwesomeService
) : CoinRepository {
    override suspend fun getExchangedValue(coins: String) = flow {
        try {
            val exchangeValue = service.exchangeValue(coins)
            val exchange = exchangeValue.values.first()
            emit(exchange)
        } catch (e: HttpException) {
            val json = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw RemoteException(errorResponse.message)
        }

    }
}