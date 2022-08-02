package com.masa.refinesearchexample.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masa.refinesearchexample.data.RefineParameterRepository
import com.masa.refinesearchexample.presentation.model.RefineParametersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    repository: RefineParameterRepository,
) : ViewModel() {

    //private val _uiState = MutableStateFlow(RefineParametersUiState.EMPTY)
    lateinit var uiState: RefineParametersUiState

    init {
        //repository.updateParameters(RefineParametersUiState.EMPTY)

        viewModelScope.launch {
            repository.uiState.collectLatest {
                uiState = it
            }
        }
    }

    fun search() {
        Log.d("MyMsg", "Searching with parameters ${uiState}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MyMsg", "SearchResultViewModel Cleared")

    }
}
