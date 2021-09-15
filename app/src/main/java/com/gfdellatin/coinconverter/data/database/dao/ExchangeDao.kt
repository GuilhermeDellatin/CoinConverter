package com.gfdellatin.coinconverter.data.database.dao

import androidx.room.*
import com.gfdellatin.coinconverter.data.model.ExchangeResponseValue
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeDao {

    @Query("SELECT * FROM tb_exchange")
    fun findAll(): Flow<List<ExchangeResponseValue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ExchangeResponseValue)

    @Query("DELETE FROM tb_exchange")
    suspend fun deleteAll()

}