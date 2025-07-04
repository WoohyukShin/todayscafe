package com.example.project1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project1.R
import androidx.navigation.NavHostController


@Composable
fun TopTabs(navController: NavController, onSelect: (String) -> Unit) {

    val tabs = listOf(
        "setting" to painterResource(R.drawable.ic_setting),
        "info" to painterResource(R.drawable.ic_info)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.tab))
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )

            Spacer(Modifier.width(8.dp))

        }

        Spacer(modifier = Modifier.weight(0.7f))

        tabs.forEach { (route, image) ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                        onSelect(route)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = image,
                    contentDescription = route,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = when (route) {
                        "setting" -> "세팅"
                        "info" -> "인포"
                        else -> route
                    },
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }

    }
}

