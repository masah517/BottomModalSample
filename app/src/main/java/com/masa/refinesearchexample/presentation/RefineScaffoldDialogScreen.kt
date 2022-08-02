package com.masa.refinesearchexample.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefineScaffoldDialogScreen(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(300.dp),
) {

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Expanded
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Bottom sheet",
                    fontSize = 60.sp
                )
            }
        },
        sheetBackgroundColor = Color.Green,
        sheetPeekHeight = 0.dp,
        backgroundColor = Color.Transparent,
    ) {
        Spacer(modifier = Modifier.fillMaxSize())
    }
}

