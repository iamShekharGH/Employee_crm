package com.shekharhandigol

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shekharhandigol.common.Destinations
import com.shekharhandigol.common.getNavDestinations
import com.shekharhandigol.home.R
import com.shekharhandigol.theme.BothPreviews
import com.shekharhandigol.theme.EmployeeCRMTheme
import kotlinx.coroutines.launch

@Composable
fun EmployeeNavDrawer(
    drawerState: DrawerState,
    navController: NavHostController,
    navigateToAttendance: () -> Unit,
    navigateToSalary: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {


    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                modifier = modifier,
            ) {
                val scope = rememberCoroutineScope()
                val currentRoute = currentRoute(navController)
                Logo()

                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.attendance_summary)) },
                    icon = { Icon(Icons.Filled.Home, null) },
                    selected = currentRoute == Destinations.AttendanceSummaryModule || currentRoute == Destinations.AttendanceHome,
                    onClick = {
                        if (!(currentRoute == Destinations.AttendanceSummaryModule || currentRoute == Destinations.AttendanceHome)) {
                            navigateToAttendance()
                            closeDrawer()
                        }
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text(stringResource(id = R.string.salary_summary)) },
                    icon = { Icon(Icons.AutoMirrored.Filled.List, null) },
                    selected = currentRoute == Destinations.SalarySummaryModule || currentRoute == Destinations.SalarySummary,
                    onClick = {
                        if (!(currentRoute == Destinations.SalarySummaryModule || currentRoute == Destinations.SalarySummary)) {
                            navigateToSalary()
                            closeDrawer()
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) { content() }


}

@Composable
fun currentRoute(navController: NavHostController): Destinations {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    return currentRoute?.getNavDestinations() ?: Destinations.Home
}

@Composable
private fun Logo() {
    Row(
        modifier = Modifier
            .padding(top = 32.dp, end = 32.dp, bottom = 32.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.srk_profile),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        /*Spacer(Modifier.width(8.dp))
        Icon(
            painterResource(R.drawable.profile_avatar),
            contentDescription = stringResource(R.string.salary_summary),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )*/
    }
}

//@Preview("Drawer contents")
//@Preview("Drawer contents (dark)", uiMode = UI_MODE_NIGHT_YES)
@BothPreviews
@Composable
fun PreviewAppDrawer() {
    EmployeeCRMTheme {
        EmployeeNavDrawer(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
            navController = NavHostController(LocalContext.current),
            navigateToAttendance = {},
            navigateToSalary = {},
            closeDrawer = { },
            modifier = Modifier,
            content = {}
        )
    }
}