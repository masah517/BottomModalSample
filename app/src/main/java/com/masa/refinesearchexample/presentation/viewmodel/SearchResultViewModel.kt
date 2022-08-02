package com.masa.refinesearchexample.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masa.refinesearchexample.data.RefineParameterRepository
import com.masa.refinesearchexample.presentation.model.RefineParametersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    repository: RefineParameterRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RefineParametersUiState.EMPTY)
    val uiState: StateFlow<RefineParametersUiState> = _uiState

    init {
        Log.d("MyMsg", "---SearchResultViewModel ${hashCode()} Initialized---")

        viewModelScope.launch {
            repository.uiState.onEach {
                Log.d("MyMsg", "---SearchResultViewModel Emit $it---")
                update(it)
            }.launchIn(viewModelScope)
        }
    }

    fun loadItems() {}

    fun search() {
        Log.d("MyMsg", "Searching with parameters ${uiState.value}")
    }

    private fun update(refineParametersUiState: RefineParametersUiState){
        _uiState.update {
            refineParametersUiState
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyMsg", "---SearchResultViewModel ${hashCode()} Cleared---")
    }
}
