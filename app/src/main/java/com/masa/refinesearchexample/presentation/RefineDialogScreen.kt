package com.masa.refinesearchexample.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.masa.compose.TypeSelector
import com.masa.refinesearchexample.presentation.viewmodel.Event
import com.masa.refinesearchexample.presentation.viewmodel.RefineModalViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefineDialogScreen(
    modifier: Modifier = Modifier,
    viewModel: RefineModalViewModel = viewModel(),
    modalDismissCallBack: (Boolean) -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        // Compose1.2より下位のバージョンだと下の方法を用いる
        // confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val scope = rememberCoroutineScope()
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val state = viewModel.state.value

    var isFirstHide by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(bottomSheetState.currentValue) {
        when (bottomSheetState.currentValue) {
            ModalBottomSheetValue.Expanded -> {
                Log.d("MyMsg", "Expanded")
            }
            else -> {
                Log.d("MyMsg", "Hidden")
                if (isFirstHide) {
                    bottomSheetState.animateTo(
                        ModalBottomSheetValue.Expanded,
                        TweenSpec(200, 0, LinearEasing) // モーダルを開く際のアニメーション(緩急)
                    )
                    isFirstHide = false
                } else {
                    modalDismissCallBack.invoke(false)
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.7).dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Text(text = "現在の絞り込み条件： ${state.type}")


                    TypeSelector { type ->
                        viewModel.emitEvent(Event.RefineTypeEvent(type))
                    }
                    // 検索実行ボタン
                    Button(
                        onClick = {
                            scope.launch {
                                bottomSheetState.hide()
                                modalDismissCallBack(true)
                            }
                        }
                    ) {
                        Text(text = "検索する")
                    }
                }
            }
        },
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(16.dp)
    ) {}
}
