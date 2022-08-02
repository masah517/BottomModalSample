package com.masa.refinesearchexample.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masa.refinesearchexample.data.RefineParameterRepository
import com.masa.refinesearchexample.presentation.model.RefineParametersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RefineModalViewModel @Inject constructor(
    private val repository: RefineParameterRepository
) : ViewModel() {

    private val _state = mutableStateOf(RefineParametersUiState.EMPTY)
    val state = _state

    fun emitEvent(event: Event) {
        when (event) {
            is Event.RefineTypeEvent -> {
                _state.value = _state.value.copy(
                    type = event.type
                )
                viewModelScope.launch {
                    repository.updateParameters(
                        _state.value
                    )
                }
            }
        }
    }
}

sealed interface Event {
    data class RefineTypeEvent(
        val type: String,
    ) : Event
}
