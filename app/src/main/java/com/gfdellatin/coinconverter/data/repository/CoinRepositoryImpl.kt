package com.gfdellatin.coinconverter.data.repository

import com.gfdellatin.coinconverter.core.extensions.exceptions.RemoteException
import com.gfdellatin.coinconverter.data.database.AppDatabase
import com.gfdellatin.coinconverter.data.model.ErrorResponse
import com.gfdellatin.coinconverter.data.model.ExchangeResponseValue
import com.gfdellatin.coinconverter.data.services.AwesomeService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CoinRepositoryImpl(
    appDatabase: AppDatabase,
    private val service: AwesomeService
) : CoinRepository {

    private val dao = appDatabase.exchangeDao()

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

    override suspend fun save(exchange: ExchangeResponseValue) {
        dao.save(exchange)
    }

    override fun list(): Flow<List<ExchangeResponseValue>> {
        return dao.findAll()
    }
}