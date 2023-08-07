package com.alexandresantos.features.presenter.genericloading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.alexandresantos.features.utils.Constants

@Composable
fun LoadingPresenter() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary, modifier = Modifier
                .align(
                    alignment = Alignment.Center
                )
                .testTag(Constants.TAG_TEST_CIRCULAR_PROGRESS_LOADING_VIEW)
        )
    }
}