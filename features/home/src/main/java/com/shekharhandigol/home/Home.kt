package com.shekharhandigol.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shekharhandigol.theme.BothPreviews
import com.shekharhandigol.ui.EmployeeCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(goToProfile: () -> Unit) {
    HomeUI(goToProfile = goToProfile)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUI(goToProfile: () -> Unit) {
    Scaffold(
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
                    IconButton(onClick = {}) {
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
        bottomBar = {
            /*BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentPadding = PaddingValues(0.dp),
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = ""
                        )
                    }

                }
            }*/
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {


            var items by remember { mutableStateOf((1..20).toList()) }
            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            var isLoading by remember { mutableStateOf(true) }

            LaunchedEffect(listState) {
                snapshotFlow { listState.layoutInfo.visibleItemsInfo }
                    .collect { visibleItems ->
                        val lastVisibleItemIndex = visibleItems.lastOrNull()?.index
                        if (lastVisibleItemIndex == items.size - 1 && !isLoading) {
                            coroutineScope.launch {
                                isLoading = true
                                // Simulate loading more data
                                delay(2000)
                                val nextItems = items.size + 1..items.size + 5
                                items = items + nextItems.toList()
                                isLoading = false
                            }
                        }
                    }
            }

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {

                items(items.size) { index ->
                    EmployeeCard()
                }
                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

            }
        }
    }
}

@BothPreviews
@Composable
fun PreviewHomeUI() {
    HomeUI {}
}
