package com.masa.refinesearchexample.presentation.model

data class RefineParametersUiState(
    val type : String,
){
    companion object{
        val EMPTY = RefineParametersUiState("")
    }

}
