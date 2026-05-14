package com.example.channapatnanammapride.presentation.verify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.channapatnanammapride.domain.model.Artisan
import com.example.channapatnanammapride.domain.model.Toy
import com.example.channapatnanammapride.domain.repository.ToyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyToyViewModel @Inject constructor(
    private val repository: ToyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<VerifyUiState>(VerifyUiState.Idle)
    val uiState: StateFlow<VerifyUiState> = _uiState.asStateFlow()

    fun verifyToy(toyId: String) {
        if (toyId.length != 6) {
            _uiState.value = VerifyUiState.Error("Please enter a valid 6-digit ID")
            return
        }

        viewModelScope.launch {
            _uiState.value = VerifyUiState.Loading
            repository.verifyToy(toyId).collect { result ->
                result.onSuccess { (toy, artisan) ->
                    _uiState.value = VerifyUiState.Success(toy, artisan)
                }.onFailure {
                    _uiState.value = VerifyUiState.Error(it.message ?: "Verification failed")
                }
            }
        }
    }

    fun reset() {
        _uiState.value = VerifyUiState.Idle
    }
}

sealed class VerifyUiState {
    object Idle : VerifyUiState()
    object Loading : VerifyUiState()
    data class Success(val toy: Toy, val artisan: Artisan) : VerifyUiState()
    data class Error(val message: String) : VerifyUiState()
}
