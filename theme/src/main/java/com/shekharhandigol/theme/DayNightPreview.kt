package com.shekharhandigol.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(name = "light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
annotation class BothPreviews
