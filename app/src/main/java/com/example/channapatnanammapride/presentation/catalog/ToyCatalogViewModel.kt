package com.example.channapatnanammapride.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.channapatnanammapride.domain.model.Toy
import com.example.channapatnanammapride.domain.repository.ToyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToyCatalogViewModel @Inject constructor(
    private val repository: ToyRepository
) : ViewModel() {

    private val _toys = MutableStateFlow<List<Toy>>(emptyList())
    val toys: StateFlow<List<Toy>> = _toys.asStateFlow()

    init {
        loadToys()
    }

    private fun loadToys() {
        viewModelScope.launch {
            repository.getToyCatalog().collect {
                _toys.value = it
            }
        }
    }
}
