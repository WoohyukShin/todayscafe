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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.R
import androidx.navigation.NavHostController



@Composable
fun HomeScreen(navController: NavHostController,
               modifier: Modifier = Modifier,
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
                    .clip(RoundedCornerShape(32.dp)) // 둥글게
                    .background(if (isSelected) Color(0xFFB388FF) else Color(0xFFF3F3F3))
                    .clickable { onSelect(tab) }
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .shadow(elevation = if (isSelected) 4.dp else 0.dp, shape = RoundedCornerShape(32.dp))
            ) {
                Text(
                    text = tab,
                    color = if (isSelected) Color.White else Color(0xFF555555),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
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
            .shadow(6.dp, RoundedCornerShape(28.dp))
            .background(Color(0xFFFEEAE6), RoundedCornerShape(28.dp))
            .padding(24.dp)
    ) {
        Column {
            Text(
                "$userName 님,\n오늘은 이런 카페 어때요?",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF4E4E4E)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(message, fontSize = 16.sp, color = Color(0xFF777777))
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onShuffle,
                modifier = Modifier
                    .align(Alignment.End)
                    .clip(RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB388FF))
            ) {
                Text("다른 추천 보기", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.img_coffee_main),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
    }
}

@Composable
fun CafeFeedList(feed: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        feed.forEach { cafeName ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(20.dp))
                    .background(Color(0xFFECEFF1), RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        cafeName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF333333)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.img_cafe_sample),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
        }
    }
}

