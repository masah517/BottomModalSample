package com.masa.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TypeSelector(
    selectorCallBack: (String) -> Unit
) {

    Row() {
        Button(onClick = {
            selectorCallBack.invoke("Left")
        }) {
            Text(text = "Left")
        }
        Button(onClick = {
            selectorCallBack.invoke("Right")
        }) {
            Text(text = "Right")
        }
    }
}
