package com.example.androidsp.ui.heroListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidsp.data.Repository
import com.example.androidsp.domain.Hero
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
class HeroListViewModel @Inject constructor(val repository: Repository): ViewModel() {
    private val _uiState: MutableStateFlow<StateHeroList> = MutableStateFlow(StateHeroList.Idle())
    val uiState: StateFlow<StateHeroList> = _uiState.asStateFlow()
    init {
        getHeroList()
    }
    fun getHeroList(){
        viewModelScope.launch{
            _uiState.update { StateHeroList.Loading }
            withContext(Dispatchers.IO) {
                repository.getHeroList().collect{heroList ->
                    _uiState.update { StateHeroList.SuccessGetHeros(heroList) }
                }
            }

        }
    }

    fun setHeroFav(hero: Hero){
        viewModelScope.launch(Dispatchers.IO) { repository.likeHero(hero) }
    }


}

sealed class StateHeroList{
    class Idle: StateHeroList()
    class Error(val message: String): StateHeroList()
    object Loading: StateHeroList()
    data class SuccessGetHeros(val heroList: List<Hero>): StateHeroList()
    object HeroSelected: StateHeroList()
    class OnHerosUpdated(val heroList: List<Hero>) : StateHeroList()
}