package com.shekharhandigol.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shekharhandigol.theme.BothPreviews


@BothPreviews
@Composable
fun StartingScreen(text: String = "Hi, Welcome!!") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Image(
            painter = painterResource(R.mipmap.male_profile_pic),
            contentDescription = "Welcome",
            modifier = Modifier.size(200.dp).clip(RoundedCornerShape(32.dp))
        )*/
        Icon(
            painter = painterResource(R.drawable.srk_profile),
            contentDescription = "Welcome",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}