package com.example.androidsp.ui.heroDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidsp.data.Repository
import com.example.androidsp.domain.Hero
import com.example.androidsp.domain.HeroLike
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val repository: Repository) : ViewModel(){
    private val _uiState: MutableStateFlow<StateHeroDetail> = MutableStateFlow(StateHeroDetail.Idle())
    val uiState: StateFlow<StateHeroDetail> = _uiState.asStateFlow()

    fun getHero(id: Int){
        viewModelScope.launch{
            _uiState.update { StateHeroDetail.Loading }

            val result = runCatching {
                withContext(Dispatchers.IO){
                    val hero = repository.getHero(id)
                    hero
                }
            }
            if(result.isSuccess){
                _uiState.update { StateHeroDetail.SuccessGetHero(result.getOrThrow()) }
            } else{
                _uiState.update { StateHeroDetail.Error(result.exceptionOrNull()?.message.orEmpty()) }
            }
        }
    }
}

sealed class StateHeroDetail{
    class Idle: StateHeroDetail()
    class Error(val message: String): StateHeroDetail()
    object Loading: StateHeroDetail()
    data class SuccessGetHero(val hero: Hero): StateHeroDetail()
    data class SuccessGetSeries(val series: List<HeroLike>): StateHeroDetail()
    data class SuccessGetComics(val series: List<HeroLike>): StateHeroDetail()
    class OnHerosUpdated(val heroList: List<HeroLike>) : StateHeroDetail()
}