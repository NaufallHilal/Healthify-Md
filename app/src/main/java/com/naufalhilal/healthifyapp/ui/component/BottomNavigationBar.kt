package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.naufalhilal.healthifyapp.R
import com.naufalhilal.healthifyapp.ui.route.NavigationItem
import com.naufalhilal.healthifyapp.ui.theme.Primary10
import com.naufalhilal.healthifyapp.ui.theme.Secondary90
import com.naufalhilal.healthifyapp.ui.theme.SurfaceContainer

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Diary,
        NavigationItem.Food,
        NavigationItem.Profile,
    )
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(containerColor = SurfaceContainer, contentColor = Black) {
        items.forEach { item ->
            val selected = currentRoute == item.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationRoute!!) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                    }
                },
                icon = {
                    when (item) {
                        NavigationItem.Home -> Icon(
                            painter = painterResource(id = R.drawable.ic_dot),
                            contentDescription = stringResource(id = item.resourceId),
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        )

                        NavigationItem.Diary -> Icon(
                            painter = painterResource(id = R.drawable.ic_dot),
                            contentDescription = stringResource(id = item.resourceId),
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        )

                        NavigationItem.Food -> Icon(
                            painter = painterResource(id = R.drawable.ic_dot),
                            contentDescription = stringResource(id = item.resourceId),
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        )

                        NavigationItem.Profile -> Icon(
                            painter = painterResource(id = R.drawable.ic_dot),
                            contentDescription = stringResource(id = item.resourceId),
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        )
                    }
                },
                label = {
                    Text(text = stringResource(id = item.resourceId))
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Secondary90,
                    selectedIconColor = Primary10,
                    unselectedIconColor = Black
                )
            )
        }
    }
}
