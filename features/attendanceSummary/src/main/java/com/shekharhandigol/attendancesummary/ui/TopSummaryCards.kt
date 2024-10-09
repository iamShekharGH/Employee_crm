package com.shekharhandigol.attendancesummary.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shekharhandigol.theme.BothPreviews

@BothPreviews
@Composable
fun PreviewTopSummaryCard() {
    TopSummaryCard(leavesTaken = 10, leavesLeft = 20)
}

@Composable
fun TopSummaryCard(leavesTaken: Int, leavesLeft: Int) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedCard(
            modifier = Modifier
                .weight(1f)
                .padding(
                    8.dp
                ),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.onErrorContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.errorContainer
            )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.errorContainer
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = leavesTaken.toString(), fontSize = 21.sp, textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Leaves Taken",
                    style = MaterialTheme.typography.titleMedium
                )
            }


        }

        ElevatedCard(
            modifier = Modifier
                .weight(1f)
                .padding(
                    8.dp
                ),
            colors = CardColors(
                containerColor = Color(0xFF008000),
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                contentColor = Color.White
            )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.errorContainer
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = leavesLeft.toString(), fontSize = 21.sp, textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Leaves Left",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
