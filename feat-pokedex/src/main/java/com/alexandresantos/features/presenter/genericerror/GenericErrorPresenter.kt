package com.alexandresantos.features.presenter.genericerror

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.alexandresantos.features.R
import com.alexandresantos.features.utils.Constants

@Composable
fun GenericErrorPresenter(
    error: String,
    onRetry: () -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .testTag(Constants.TAG_TEST_IMAGE_GENERIC_ERROR)
            )
            ElevatedButton(onClick = { onRetry() },
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .testTag(Constants.TAG_TEST_RETRY_BUTTON_GENERIC_ERROR)
            ) {
                Text(text = "Retry")
            }
        }
    }
}