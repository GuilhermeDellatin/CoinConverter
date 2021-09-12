package com.gfdellatin.coinconverter.presentation

import androidx.lifecycle.*
import com.gfdellatin.coinconverter.data.model.ExchangeResponseValue
import com.gfdellatin.coinconverter.domain.ListExchangeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val listExchangeViewModel: ListExchangeUseCase
) : ViewModel(), LifecycleObserver {

    private val _state = MutableLiveData<HistoryViewModel.State>()
    val state: LiveData<HistoryViewModel.State> = _state

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun getExchanges() {
        viewModelScope.launch {
            viewModelScope.launch {
                listExchangeViewModel()
                    .flowOn(Dispatchers.Main)
                    .onStart {
                        _state.value = State.Loading
                    }
                    .catch {
                        _state.value = State.Error(it)
                    }
                    .collect {
                        _state.value = State.Success(it)
                    }
            }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val list: List<ExchangeResponseValue>) : State()
        data class Error(val error: Throwable) : State()
    }

}