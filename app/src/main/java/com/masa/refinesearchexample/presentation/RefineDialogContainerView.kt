package com.masa.refinesearchexample.presentation

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView

class RefineDialogContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractComposeView(context, attrs, defStyleAttr) {

    lateinit var modalDismissCallback: (Boolean) -> Unit

    @Composable
    override fun Content() {
        RefineDialogScreen(
            modifier = Modifier,
            modalDismissCallBack = this.modalDismissCallback,
        )
    }

    //  By default, Compose uses the DisposeOnDetachedFromWindow strategy (確認のためだけにこのメソッド書いています)
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("MyMsg", "Compose DetachedFromWindow")
    }
}
