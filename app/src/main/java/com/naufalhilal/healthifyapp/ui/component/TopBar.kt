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

    val navigationIcon = navBackStackEntry?.destination?.route?.let {
        when (it) {
            NavigationItem.Home.route -> Icons.Filled.Menu
            NavigationItem.Diary.route -> Icons.Filled.Menu
            NavigationItem.Food.route -> Icons.Filled.Menu
            NavigationItem.Profile.route -> Icons.Filled.Menu
            else -> Icons.Filled.ArrowBack
        }
    } ?: Icons.Filled.ArrowBack

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
        }
    )
}
