package com.gfdellatin.coinconverter.presentation.di

import com.gfdellatin.coinconverter.presentation.HistoryViewModel
import com.gfdellatin.coinconverter.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModules {
    fun load() {
        loadKoinModules(viewModelModules())
    }

    private fun viewModelModules(): Module {
        return module {
            viewModel { MainViewModel(get(), get()) }
            viewModel { HistoryViewModel(get(), get()) }
        }
    }
}