package com.shekharhandigol.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shekharhandigol.data.models.EmployeeGender
import com.shekharhandigol.data.models.UserInformation
import com.shekharhandigol.theme.BothPreviews
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(goToHome: () -> Unit) {
    val viewModel: ProfileScreenViewModel = hiltViewModel()
    val state = viewModel.profileUIState.collectAsStateWithLifecycle()
    when (val ui = state.value) {
        ProfileUiState.NoInfoAvailable -> {
            EmptyProfileScreen()
        }

        is ProfileUiState.ThisIsProfileInfo -> {
            ProfileUI(goToHome, ui.userInformation)
        }
    }
}

@BothPreviews
@Composable
fun EmptyProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            text = "No Profile Found",
            fontSize = 21.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.displayLarge
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUI(goToHome: () -> Unit, userInformation: UserInformation) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                title = {
                    Text("Top app bar")
                },
                navigationIcon = {
                    IconButton(onClick = { goToHome() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }

                },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Nope! Not there")
                            goToHome()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Check"
                        )
                    }
                }
            )
        },
        bottomBar = {
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(userInformation.photoUrl.ifEmpty {
                                "https://i.pinimg.com/originals/4d/10/eb/4d10eb4d81723343bfc7e3f9dac8f9aa.jpg"
                            }).build(),
                        placeholder = painterResource(R.mipmap.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(end = 8.dp)
                            .size(150.dp)

                    )
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = userInformation.name,
                            style = MaterialTheme.typography.labelLarge,
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = userInformation.title,
                            style = MaterialTheme.typography.labelLarge
                        )
                        /*Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "+91 9090909090",
                            style = MaterialTheme.typography.labelMedium
                        )*/
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = userInformation.email.ifEmpty { "abcd@abcd.com" },
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "${userInformation.age} years old.",
                            style = MaterialTheme.typography.labelMedium
                        )
                        /*
                        name: String,
                        title: String,
                        email: String,
                        age: Int,
                        birthday: String,
                        photoUrl: String,
                        employeeGender: EmployeeGender,
                        presentToday: Boolean,
                        salaryCredited: Boolean
                         */
                    }

                }


            }

            ElevatedCard(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Marked Attendance",
                        fontSize = 21.sp,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ElevatedButton(onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("You have came!!")
                            }
                        }, enabled = userInformation.presentToday) {
                            Text("Present")
                        }
                        ElevatedButton(onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Why have You not came??")
                            }
                        }, enabled = !userInformation.presentToday) {
                            Text("Absent")
                        }
                    }
                }

            }



            ElevatedCard(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Salary Credited",
                        fontSize = 21.sp,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ElevatedButton(onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("You have came!!")
                            }
                        }, enabled = userInformation.salaryCredited) {
                            Text("Yes")
                        }
                        ElevatedButton(onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Why have You not came??")
                            }
                        }, enabled = !userInformation.salaryCredited) {
                            Text("No")
                        }
                    }
                }

            }


            ElevatedCard(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Performance Rating",
                        fontSize = 21.sp,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = "TBD")
                        for (i in 1..5) {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp, contentDescription = "star",
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }


            }


        }

    }

}

@BothPreviews
@Composable
fun PreviewProfileUI() {
    ProfileUI(
        { }, UserInformation(
            eid = 1,
            name = "Shekhar Handigol",
            title = "Software Engineer",
            age = 25,
            birthday = "1997-01-01",
            presentToday = true,
            salaryCredited = false,
            email = "william.strong@my-own-personal-domain.com",
            employeeGender = EmployeeGender.Male,
            photoUrl = ""
        )
    )
}