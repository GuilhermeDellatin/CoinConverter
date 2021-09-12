package com.gfdellatin.coinconverter.domain.di

import com.gfdellatin.coinconverter.domain.GetExchangeValueUseCase
import com.gfdellatin.coinconverter.domain.ListExchangeUseCase
import com.gfdellatin.coinconverter.domain.SaveExchangeUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModules {
    fun load() {
        loadKoinModules(useCaseModules())
    }

    private fun useCaseModules(): Module {
        return module {
            factory { GetExchangeValueUseCase(get()) }
            factory { ListExchangeUseCase(get()) }
            factory { SaveExchangeUseCase(get()) }
        }
    }
}