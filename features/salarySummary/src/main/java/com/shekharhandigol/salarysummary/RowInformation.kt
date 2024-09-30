package com.shekharhandigol.salarysummary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shekharhandigol.theme.BothPreviews

@Composable
fun RowInformation(heading: String, body: String, larger: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = heading,
            style = if (larger) MaterialTheme.typography.titleLarge else MaterialTheme.typography.titleMedium
        )
        Text(
            text = body,
            style = if (larger) MaterialTheme.typography.titleLarge else MaterialTheme.typography.labelLarge
        )
    }
}

@BothPreviews
@Composable
fun PreviewRowInformation() {
    RowInformation("Basic Salary", "₹66,667")
}