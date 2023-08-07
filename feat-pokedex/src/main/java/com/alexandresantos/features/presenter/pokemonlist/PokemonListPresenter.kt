package com.alexandresantos.features.presenter.pokemonlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.alexandresantos.commons.calcDominantColor
import com.alexandresantos.features.presenter.genericerror.GenericErrorPresenter
import com.alexandresantos.features.presenter.genericloading.LoadingPresenter
import com.alexandresantos.features.presenter.models.PokemonListItemPresentation
import com.alexandresantos.features.router.Destination
import com.alexandresantos.features.ui.theme.PokedexShapes
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun PokemonListPresenter(
    navController: NavController,
    viewModel: PokemonListViewModel = koinViewModel()
) {
    val currentState by viewModel.listState.collectAsState()
    when {
        currentState.loading -> {
            LoadingPresenter()
        }

        currentState.error.isNotEmpty() -> {
            GenericErrorPresenter(error = currentState.error) {
                viewModel.getPokemonList()
            }
        }

        currentState.list.isNotEmpty() -> {
            PokemonListContent(navController = navController, state = currentState)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun PokemonListContent(
    navController: NavController,
    viewModel: PokemonListViewModel = koinViewModel(),
    state: PokemonListState

) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(Modifier.padding(it)) {
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar(
                    hint = "Pesquise um pokemon por nome",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                ) { text ->
                    viewModel.searchPokemon(text)
                }
                PokemonList(navController = navController, state = state)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(
            value = text, onValueChange = { inputText ->
                text = inputText
                onSearch(inputText)
                isHintDisplayed = inputText.isEmpty()
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .testTag("TestingSearch")
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokemonListViewModel = koinViewModel(),
    gridSize: Int = 2,
    state: PokemonListState
) {
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridSize),
        userScrollEnabled = true,
        contentPadding = PaddingValues(bottom = 64.dp),
        state = listState
    ) {
        itemsIndexed(state.list) { index, pokemon ->
            if (index >= state.list.size - 1 && !state.loading && !state.isSearching) {
                LaunchedEffect(key1 = true) {
                    viewModel.getPokemonList()
                }
            }
            PokemonItem(
                navController = navController,
                pokemon = pokemon
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonItem(
    pokemon: PokemonListItemPresentation,
    navController: NavController
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp),
        onClick = {
            navController.navigate(
                Destination.PokemonDetail.buildRoute(
                    pokemonName = pokemon.name,
                    dominantColor = dominantColor.toArgb()
                )
            )
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        shape = PokedexShapes.medium,
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .background(dominantColor),
            horizontalAlignment = CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.url).build(),
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.5f)
                    )
                },
                onSuccess = {
                    calcDominantColor(it.result.drawable) { color ->
                        dominantColor = Color(color)
                    }
                },
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally),
                contentDescription = "Ver detalhes"
            )
            Text(
                text = pokemon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .testTag("pokemonNameTag"),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }

}



