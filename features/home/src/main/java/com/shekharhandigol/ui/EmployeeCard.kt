package com.shekharhandigol.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shekharhandigol.data.models.Employee
import com.shekharhandigol.data.models.Gender
import com.shekharhandigol.home.R
import com.shekharhandigol.theme.BothPreviews

@Composable
fun EmployeeCard(
    employee: Employee
) {
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
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f))
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://picsum.photos/800/800")
                            .crossfade(true)
                            .build(),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(150.dp)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.tertiary,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp)),
                        placeholder = painterResource(R.mipmap.male_profile_pic),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "profile pic"

                    )
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(4.dp),
                            text = employee.eid.toString(),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = TextStyle(fontWeight = FontWeight.ExtraBold)
                        )
                    }

                }


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    employee.apply {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = name, fontSize = 21.sp)
                            Icon(
                                painter = if (gender == Gender.MALE) painterResource(R.drawable.male) else painterResource(
                                    R.drawable.female
                                ),
                                contentDescription = "gender"
                            )
                        }
                        Text(
                            text = title,
                            fontStyle = FontStyle.Italic,
                            fontSize = 15.sp
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Present Today")
                            AYesNoSwitch(presentToday)
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Salary Credited")
                            AYesNoSwitch(salaryCredited)
                        }
                    }
                }
            }
        }
    }
}

@BothPreviews
@Composable
fun AYesNoSwitch(isYes: Boolean = true) {
    MaterialTheme.colorScheme.apply {
//        TextField(value = "Yes/No", onValueChange = {}, modifier = Modifier.padding(4.dp))
        Text(
            text = if (isYes) {
                "YES"
            } else {
                "NO"
            },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(
                    shape = RoundedCornerShape(2.dp), brush = Brush.radialGradient(
                        colors = listOf(
                            tertiaryContainer,
                            tertiaryContainer.copy(alpha = 0.2f),
                            tertiaryContainer.copy(alpha = 0.2f),
                            tertiaryContainer.copy(alpha = 0.2f),
                            tertiaryContainer.copy(alpha = 0.2f),
                            tertiaryContainer,
                        )
                    )
                )
                .border(
                    border = BorderStroke(2.dp, onTertiaryContainer),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp),
            style = TextStyle(fontWeight = FontWeight.ExtraBold)
        )

    }
}

@BothPreviews
@Composable
fun PreviewEmployeeCard() {
    EmployeeCard(
        Employee(
            eid = 1234,
            name = "Rakesh Jhun Jhun",
            title = "Serial Jhadu Poocha",
            presentToday = true,
            salaryCredited = false,
            gender = Gender.MALE,
            photoUrl = ""
        )
    )
}