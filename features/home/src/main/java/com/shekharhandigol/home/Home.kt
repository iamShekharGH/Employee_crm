package com.shekharhandigol.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
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
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.theme.BothPreviews
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    goToProfile: () -> Unit,
    viewModel: HomeScreenViewModel
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    HomeUI(goToProfile = goToProfile, uiState)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUI(goToProfile: () -> Unit, uiState: State<HomeUiState>) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val state = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .pullToRefresh(
                onRefresh = {
                    isRefreshing = true
                    scope.launch {
                        isRefreshing = true
                        snackbarHostState.showSnackbar("Shekhar")
                        delay(500L)
                        isRefreshing = false
                    }
                },
                state = state,
                isRefreshing = isRefreshing

            ),
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
                    Text("Employees")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Shekhar")
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = ""
                        )
                    }

                },
                actions = {
                    IconButton(onClick = { goToProfile() }) {
                        Icon(
                            painter = painterResource(R.drawable.profile_avatar),
                            contentDescription = ""
                        )
                    }

                }
            )
        },
    ) { innerPadding ->
        when (val ui = uiState.value) {
            HomeUiState.Starting -> {
                StartingScreen()
            }

            HomeUiState.Empty -> {
                StartingScreen("Employee List is Empty.")
            }

            HomeUiState.Error -> {

            }

            is HomeUiState.EmployeeList -> {
                EmployeeListScreen(innerPadding, ui.list)
            }
        }


        PullToRefreshDefaults.Indicator(
            state = state, isRefreshing = isRefreshing,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@BothPreviews
@Composable
fun PreviewHomeUI() {
    HomeUI({}, rememberUpdatedState(HomeUiState.Empty))
}
