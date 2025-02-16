package com.example.countrymaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countrymaster.ui.theme.CountryMasterTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Random

class nationentorno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryMasterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel<GameViewModel>()
                    )
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier, viewModel: GameViewModel) {
    val currentProblem by viewModel.currentProblem.collectAsState()
    val news by viewModel.news.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Nación: Jandrek Quin")

        Spacer(modifier = Modifier.height(16.dp))

        if (currentProblem != null) {
            Text(text = currentProblem!!.description, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            currentProblem!!.options.forEachIndexed { index, option ->
                Button(onClick = { viewModel.handleOptionSelection(option) }) {
                    Text(text = option.text)
                }
            }
        } else {
            Text(text = "No hay más problemas.")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = news, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    CountryMasterTheme {
        GameScreen(viewModel = viewModel())
    }
}

// ViewModel para manejar el estado del juego
class GameViewModel : ViewModel() {

    private val _currentProblem = MutableStateFlow<Problem?>(null)
    val currentProblem: StateFlow<Problem?> = _currentProblem

    private val _news = MutableStateFlow<String>("")
    val news: StateFlow<String> = _news

    init {
        loadNextProblem()
    }

    fun loadNextProblem() {
        viewModelScope.launch {
            _currentProblem.value = problems.random()
        }
    }

    fun handleOptionSelection(option: Option) {
        viewModelScope.launch {
            _news.value = "Ley nueva: ${option.result}"
            loadNextProblem()
        }
    }
}

// Definición de problemas y opciones
data class Problem(
    val description: String,
    val options: List<Option>
)

data class Option(
    val text: String,
    val result: String
)

val problems = listOf(
    Problem(
        "¿Cómo manejar la crisis económica?",
        listOf(
            Option("Aumentar impuestos", "La economía se estabiliza pero la población está insatisfecha."),
            Option("Reducir gastos públicos", "Los servicios públicos disminuyen pero se controla el déficit."),
            Option("Pedir ayuda internacional", "Se recibe ayuda pero con condiciones restrictivas."),
            Option("Imprimir más dinero", "Se genera inflación pero se paga la deuda.")
        )
    ),
    Problem(
        "¿Qué hacer con la contaminación ambiental?",
        listOf(
            Option("Implementar leyes estrictas", "La contaminación disminuye pero las empresas se quejan."),
            Option("Promover energías renovables", "Se invierte en energía limpia pero es costoso."),
            Option("Ignorar el problema", "La contaminación aumenta pero no hay gastos adicionales."),
            Option("Crear conciencia pública", "La población se involucra pero los cambios son lentos.")
        )
    )
    // Agrega más problemas según sea necesario
)