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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               userName: String = "지영") {
    var selectedTab by remember { mutableStateOf("카페 큐레이션") }
    var todayIndex by remember { mutableStateOf(0) }

    val todayCafeList = listOf(
        "햇살 가득한 테라스 카페",
        "잔잔한 음악이 흐르는 감성 카페",
        "디저트가 맛있는 분위기 좋은 카페"
    )

    val feedMap = mapOf(
        "카페 큐레이션" to listOf("온기 한 스푼 카페", "커피맛집 카페"),
        "팔로워" to listOf("지영이 팔로우한 카페", "서진이 픽한 카페"),
        "내 카페리스트" to listOf("내가 저장한 카페1", "내가 저장한 카페2")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    )
 {
        TopBar()
        Spacer(modifier = Modifier.height(16.dp))
        TagTabs(selectedTab) { selectedTab = it }
        Spacer(modifier = Modifier.height(20.dp))
        TodayCafeCard(userName, todayCafeList[todayIndex]) {
            todayIndex = (todayIndex + 1) % todayCafeList.size
        }
        Spacer(modifier = Modifier.height(24.dp))
        CafeFeedList(feedMap[selectedTab] ?: emptyList())
    }
}

@Composable
fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_leaf),
            contentDescription = "logo",
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("홈", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "menu"
            )
        }
    }
}

@Composable
fun TagTabs(selected: String, onSelect: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        listOf("팔로워", "카페 큐레이션", "내 카페리스트").forEach { tab ->
            val isSelected = tab == selected
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (isSelected) Color(0xFFB3E5FC) else Color.LightGray)
                    .clickable { onSelect(tab) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = tab,
                    color = if (isSelected) Color.Black else Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun TodayCafeCard(userName: String, message: String, onShuffle: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFE0DC), RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column {
            Text("$userName 님,\n오늘은 이런 카페 어때요?", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(message, fontSize = 16.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onShuffle,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("다른 추천 보기")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.img_coffee_main),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun CafeFeedList(feed: List<String>) {
    Column {
        feed.forEach { cafeName ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0xFFDFF1FF), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text(cafeName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Image(
                        painter = painterResource(id = R.drawable.img_cafe_sample),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
        }
    }
}
