package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavBackStackEntry
import com.naufalhilal.healthifyapp.ui.route.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navBackStackEntry: NavBackStackEntry?,
    onMenuIconClick: () -> Unit
) {

    val title = navBackStackEntry?.destination?.route?.let {
        when (it) {
            NavigationItem.Home.route -> "Home"
            NavigationItem.Diary.route -> "Diary"
            NavigationItem.Food.route -> "Food"
            NavigationItem.Profile.route -> "Profile"
            else -> "HealthifyApp"
        }
    } ?: "HealthifyApp"

    // Check if the current route is a top-level route
    val isTopLevelRoute = navBackStackEntry?.destination?.route in listOf(
        NavigationItem.Home.route
    )

    val navigationIcon = if (isTopLevelRoute) {
        Icons.Filled.Menu
    } else {
        Icons.Filled.ArrowBack
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuIconClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            // Add any actions you want here
        }
    )
}
