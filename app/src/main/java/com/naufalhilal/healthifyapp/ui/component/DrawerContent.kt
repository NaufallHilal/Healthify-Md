package com.naufalhilal.healthifyapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.naufalhilal.healthifyapp.ui.theme.Primary100
import com.naufalhilal.healthifyapp.ui.theme.PurpleGrey40
import com.naufalhilal.healthifyapp.ui.theme.Secondary90

@Composable
fun DrawerContent(
    onItemClick: (String) -> Unit,
    drawerState: DrawerState
) {
    ModalDrawerSheet(
        content = {
            Column(
                modifier = defaultModifier(),
            ) {
                ProfileSection()
                DrawerMenu(onItemClick)
            }
        }
    )
}

@Composable
fun defaultModifier() = Modifier
    .background(color = Secondary90)
    .width(275.dp)

@Composable
fun ProfileSection() {
    Box(
        modifier = defaultModifier()
            .padding(30.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://www.chennaigrocers.com/cdn/shop/files/Green-Apple.jpg?v=1694866262")
                .crossfade(true)
                .build(),
            contentDescription = "Image Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .border(width = 2.dp, color = PurpleGrey40, CircleShape)
                .size(150.dp)
        )
    }
}

@Composable
fun DrawerMenu(onItemClick: (String) -> Unit) {
    LazyColumn(
        modifier = defaultModifier()
            .background(color = Primary100)
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        val itemsMap = mapOf(
            "Home" to Icons.Default.Home,
            "Diary" to Icons.Default.MenuBook,
            "Food" to Icons.Default.Fastfood,
            "Profile" to Icons.Default.Person
        )
        itemsMap.forEach { (itemText, icon) ->
            item {
                DrawerItem(
                    icon = icon,
                    text = itemText,
                    onItemClick = { onItemClick(itemText) }
                )
            }
        }
    }
}

@Composable
fun DrawerItem(icon: ImageVector, text: String, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onItemClick),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Primary100, contentColor = Color.Black),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(1.dp, SolidColor(Secondary90))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$text Icon"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun DrawerContentPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    DrawerContent(onItemClick = {}, drawerState = drawerState)
}
