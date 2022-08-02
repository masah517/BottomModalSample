package com.masa.refinesearchexample.data

import android.util.Log
import com.masa.refinesearchexample.presentation.model.RefineParametersUiState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface RefineParameterRepository {
    val uiState: SharedFlow<RefineParametersUiState>
    suspend fun updateParameters(parametersUiState: RefineParametersUiState)
}

class RefineParameterRepositoryImpl @Inject constructor() : RefineParameterRepository {

    private val _uiState = MutableSharedFlow<RefineParametersUiState>()
    override val uiState: SharedFlow<RefineParametersUiState> = _uiState

    override suspend fun updateParameters(parametersUiState: RefineParametersUiState) {
        Log.d("MyMsg", "Repository Emitted")
        _uiState.emit(
            parametersUiState
        )
    }
}
