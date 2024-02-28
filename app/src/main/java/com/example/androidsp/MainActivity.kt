package com.example.androidsp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.androidsp.domain.Hero
import com.example.androidsp.ui.components.HeroList
import com.example.androidsp.ui.theme.AndroidSPTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HeroListViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        val state by viewModel.uiState.collectAsState()
                        when(state){
                            is StateHeroList.SuccessGetHeros-> {
                                println((state as StateHeroList.SuccessGetHeros).heroList[0].photo)
                                HeroList(heros = (state as StateHeroList.SuccessGetHeros).heroList, modifier = Modifier.padding(it))
                            }
                            else -> {

                            }
                        }
                    }
                }
            }
        }
    }

}



private fun generateUIHeros(size: Int) = (0 until size).map { Hero("name $it",  "photo$it",true) }
