package com.shekharhandigol.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shekharhandigol.home.R
import com.shekharhandigol.theme.BothPreviews

@Composable
fun EmployeeCard() {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        onClick = {}

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row {
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(150.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(R.mipmap.male_profile_pic),
                    contentDescription = "profile pic",

                    )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Shekhar Handigol", fontSize = 21.sp)
                    Text(
                        text = "Android Developer",
                        fontStyle = FontStyle.Italic,
                        fontSize = 15.sp
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Present Today")
                        Switch(

                            checked = true,
                            onCheckedChange = {

                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                                uncheckedThumbColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                uncheckedTrackColor = MaterialTheme.colorScheme.tertiaryContainer,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Salary Credited")

                        Switch(
                            checked = true,
                            onCheckedChange = {

                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                                uncheckedThumbColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                uncheckedTrackColor = MaterialTheme.colorScheme.tertiaryContainer,
                            )
                        )
                    }
                }
            }
        }
    }
}


@BothPreviews
@Composable
fun PreviewEmployeeCard() {
    EmployeeCard()
}