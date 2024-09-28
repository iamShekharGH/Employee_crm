package com.shekharhandigol.attendancesummary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shekharhandigol.theme.BothPreviews

@Composable
fun AttendanceDaysUI() {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = "Fri, 27th Sept",
            style = MaterialTheme.typography.titleSmall
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //sick leave,
            //vacation leave, casual leave
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = "Sick leave",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            VerticalDivider(
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .height(50.dp)
                    .padding(start = 8.dp, end = 8.dp)
                    .clip(RoundedCornerShape(1.dp))
            )
            Text(
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                modifier = Modifier
                    .weight(2f)
                    .padding(bottom = 4.dp),
                style = MaterialTheme.typography.labelLarge,
                text = "Appointments for check-ups, vaccinations, or other preventive medical procedures.",
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.tertiary,
        )
    }
}

@BothPreviews
@Composable
fun PreviewAttendanceDaysUI() {
    AttendanceDaysUI()
}