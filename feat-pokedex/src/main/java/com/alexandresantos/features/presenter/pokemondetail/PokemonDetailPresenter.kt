package com.alexandresantos.features.presenter.pokemondetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.alexandresantos.features.presenter.genericerror.GenericErrorPresenter
import com.alexandresantos.features.presenter.genericloading.LoadingPresenter
import com.alexandresantos.features.presenter.models.PokemonPresentation
import com.alexandresantos.features.presenter.models.TypePresentation
import com.alexandresantos.features.utils.Constants.pokemonImageUrl
import com.alexandresantos.features.utils.extensions.toPokemonImageUrl
import com.alexandresantos.features.utils.parseStatToAbbr
import com.alexandresantos.features.utils.parseStatToColor
import com.alexandresantos.features.utils.parseTypeToColor
import org.koin.androidx.compose.koinViewModel
import kotlin.math.round

@Composable
fun PokemonDetailPresenter(
    pokemonName: String,
    dominantColor: Color,
    navController: NavController,
    viewModel: PokemonDetailViewModel = koinViewModel()
) {
    viewModel.getPokemonDetail(pokemonName)
    val currentState by viewModel.pokemonDetailState.collectAsState()

    when {
        currentState.loading -> {
            LoadingPresenter()
        }

        currentState.error.isNotEmpty() -> {
            GenericErrorPresenter(error = currentState.error) {
                viewModel.getPokemonDetail(pokemonName)
            }
        }

        currentState.pokemonDetail.name.isNotEmpty() -> {
            PokemonDetailContent(
                navController = navController,
                pokemonInfo = currentState.pokemonDetail,
                dominantColor = dominantColor
            )
        }
    }
}

@Composable
fun PokemonDetailContent(
    pokemonInfo: PokemonPresentation,
    dominantColor: Color,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PokemonDetailTop(
            navController = navController,
            pokemonInfo = pokemonInfo,
            dominantColor = dominantColor,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.TopCenter)
        )
        PokemonDetailInfo(
            pokemonInfo = pokemonInfo,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 300.dp)
                .align(Alignment.BottomCenter)

        )
    }
}


@Composable
fun PokemonDetailTop(
    navController: NavController,
    pokemonInfo: PokemonPresentation,
    dominantColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier.background(
            Color.DarkGray
        )
    ) {
        Box(
            contentAlignment = Alignment.TopCenter, modifier = Modifier
                .padding(bottom = 16.dp)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 50.dp,
                        bottomStart = 50.dp
                    )
                )
                .background(
                    Brush.verticalGradient(
                        listOf(
                            dominantColor,
                            Color.White
                        )
                    )
                )
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar para a tela anterior",
                    tint = Color.White,
                    modifier = Modifier
                        .size(36.dp)
                        .offset(x = 16.dp, y = 16.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    "# ${pokemonInfo.id}", fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier
                        .offset(y = 16.dp)
                        .padding(end = 32.dp)
                        .semantics { contentDescription = "Número do pokemon ${pokemonInfo.id}" }
                )
            }
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemonImageUrl.toPokemonImageUrl(pokemonInfo.id)).build(),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .offset(y = 20.dp)
            )
        }
    }
}

@Composable
fun PokemonDetailInfo(
    pokemonInfo: PokemonPresentation,
    modifier: Modifier = Modifier
) {
    PokemonDetailSection(
        pokemonInfo = pokemonInfo,
        modifier = modifier.offset(y = (-50).dp)
    )

}

@Composable
fun PokemonDetailSection(
    pokemonInfo: PokemonPresentation,
    modifier: Modifier = Modifier
) {
    val scrollTate = rememberScrollState()
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollTate)
    ) {
        Text(
            text = pokemonInfo.name,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        PokemonTypeSection(typeResponses = pokemonInfo.type)
        PokemonDetailInfoSection(
            pokemonWeight = pokemonInfo.weight,
            pokemonHeight = pokemonInfo.height
        )
        PokemonBaseStats(pokemonInfo = pokemonInfo)
    }
}

@Composable
fun PokemonTypeSection(typeResponses: List<TypePresentation>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .semantics { contentDescription = "Tipos do Pokemon" }

    ) {
        for (type in typeResponses) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(type))
                    .height(36.dp)
            ) {
                Text(
                    text = type.type.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold

                )
            }
        }
    }
}

@Composable
fun PokemonDetailInfoSection(
    pokemonWeight: Int,
    pokemonHeight: Int
) {

    val pokemonWeightInKg = remember {
        round(pokemonWeight * 100f) / 1000f
    }
    val pokemonHeightInMeters = remember {
        round(pokemonHeight * 100f) / 1000f
    }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        PokemonDetailDataItem(
            dataValue = pokemonWeightInKg,
            dataUnit = "kg",
            dataText = "Weight",
            modifier = Modifier
                .weight(1f)
                .semantics { contentDescription = "Peso do Pokemon" }

        )
        PokemonDetailDataItem(
            dataValue = pokemonHeightInMeters,
            dataUnit = "m",
            dataText = "Height",
            modifier = Modifier
                .weight(1f)
                .semantics { contentDescription = "Altura do Pokemon" }
        )

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PokemonDetailDataItem(
    dataValue: Float,
    dataUnit: String,
    dataText: String,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "$dataValue $dataUnit",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = dataText,
            modifier = Modifier.semantics { invisibleToUser() },
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun PokemonStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 28.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else 0f,
        animationSpec = tween(animDuration, animDelay)
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .width(70.dp)
                .padding(top = 2.dp, start = 16.dp),
            text = statName,
            color = Color.White,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .padding(end = 16.dp)
                .clip(CircleShape)
                .background(
                    if (isSystemInDarkTheme()) {
                        Color(0xFF505050)
                    } else {
                        Color.LightGray
                    }
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(curPercent.value)
                    .clip(CircleShape)
                    .background(statColor)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = (curPercent.value * statMaxValue).toInt().toString(),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun PokemonBaseStats(
    pokemonInfo: PokemonPresentation,
    animationDelayPerItem: Int = 100,
) {
    val maxBaseStat = remember {
        pokemonInfo.stat.maxOf { it.baseStat }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Base stats:",
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            modifier = Modifier.align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        for (i in pokemonInfo.stat.indices) {
            val stat = pokemonInfo.stat[i]
            PokemonStat(
                statName = parseStatToAbbr(stat),
                statValue = stat.baseStat,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat),
                animDelay = i * animationDelayPerItem
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}