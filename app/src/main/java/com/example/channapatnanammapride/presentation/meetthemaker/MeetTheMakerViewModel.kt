package com.example.channapatnanammapride.presentation.meetthemaker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.channapatnanammapride.domain.model.Workshop
import com.example.channapatnanammapride.domain.repository.ToyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeetTheMakerViewModel @Inject constructor(
    private val repository: ToyRepository
) : ViewModel() {

    private val _workshops = MutableStateFlow<List<Workshop>>(emptyList())
    val workshops: StateFlow<List<Workshop>> = _workshops.asStateFlow()

    init {
        loadWorkshops()
    }

    private fun loadWorkshops() {
        viewModelScope.launch {
            repository.getWorkshops().collect {
                _workshops.value = it
            }
        }
    }
}
