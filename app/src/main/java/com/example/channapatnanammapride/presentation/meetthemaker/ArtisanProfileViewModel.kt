package com.example.channapatnanammapride.presentation.meetthemaker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.channapatnanammapride.domain.model.Artisan
import com.example.channapatnanammapride.domain.repository.ToyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtisanProfileViewModel @Inject constructor(
    private val repository: ToyRepository
) : ViewModel() {

    private val _artisan = MutableStateFlow<Artisan?>(null)
    val artisan: StateFlow<Artisan?> = _artisan.asStateFlow()

    fun loadArtisan(artisanId: String) {
        viewModelScope.launch {
            repository.getArtisan(artisanId).collect {
                _artisan.value = it
            }
        }
    }
}
