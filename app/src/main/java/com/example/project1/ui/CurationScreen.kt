package com.example.project1.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project1.R

data class CafeItem(
    val name: String,
    val tags: String,
    val imageRes: Int,
    val scrapCount: Int
)
data class Question(
    val emoji: String,
    val question: String,
    val hint: String = "입력해주세요..."
)

data class Recommendation(val title: String, val description: String, val imageRes: Int)

val recommendationList = listOf(
    Recommendation("Strawberry Latte", "Fresh & Sweet", R.drawable.img_cafe_sample),
    Recommendation("Matcha Cream", "Earthy & Smooth", R.drawable.img_cafe_sample2),
    Recommendation("Caramel Macchiato", "Rich & Sweet", R.drawable.img_cafe_sample3)
)

data class InfoItem(val title: String, val content: String)

val cardInfoList = listOf(
    InfoItem("우리의 철학", "카페는 분위기입니다."),
    InfoItem("지역 큐레이터 소개", "믿을 수 있는 추천"),
    InfoItem("문의하기", "언제든지 편하게 연락주세요.")
)


@Composable
fun RecommendationCard(item: Recommendation) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(end = 8.dp)
            .width(160.dp)
            .height(200.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(item.title, fontWeight = FontWeight.Bold)
                Text(item.description, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun InfoCard(item: InfoItem) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(end = 8.dp)
            .width(200.dp)
            .height(100.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(item.title, fontWeight = FontWeight.Bold)
            Text(item.content, fontSize = 12.sp, color = Color.Gray)
        }
    }
}


@Composable
fun CurationScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableStateOf("카페 큐레이션") }
    var selectedFilter by remember { mutableStateOf("전체 검색") }
    var searchText by remember { mutableStateOf("") }

    val beige = colorResource(R.color.beige)
    val brown = Color(0xFF7A4E2D)

    Scaffold(
        topBar = {
            TopTabs(navController = navController) {}
        },
        bottomBar = {
            BottomTabs(navController = navController, selectedTab) { selectedTab = it }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(beige)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                text = "카페 큐레이션",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "오늘의 당신의 카페를 찾아보세요",
                fontSize = 14.sp,
                color = brown.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("전체 검색", "맞춤형", "요즘 핫한").forEach { label ->
                    Button(
                        onClick = { selectedFilter = label },
                        colors = ButtonDefaults.buttonColors(containerColor = if (label == selectedFilter) brown else Color.Transparent),
                        shape = RoundedCornerShape(30.dp),
                        border = BorderStroke(1.dp, brown),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .height(40.dp)
                    ) {
                        Text(
                            text = label,
                            color = if (label == selectedFilter) Color.White else brown,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF8E3B6),
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                when (selectedFilter) {
                    "전체 검색" -> {
                        Column {
                            OutlinedTextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                placeholder = { Text("카페를 검색해보세요...") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp),
                                trailingIcon = {
                                    Icon(Icons.Default.Search, contentDescription = null)
                                }
                            )

                            val cafeList = listOf(
                                Triple("XXXXXX카페", listOf("#공부하기 좋은", "#조용한", "#2층"), "13"),
                                Triple("XXXXXX 카페", listOf("#디저트가 맛있는", "#감성있는", "#고양이가 있는"), "4"),
                                Triple("XXXXX 카페", listOf("#데이트 하기 좋은", "#아늑한", "#주차장 있는"), "0"),
                                Triple("XXXXXX 카페", listOf("#달달한 디저트", "#조용한", "#예쁜 조명"), "7"),
                                Triple("XXXXXXX 카페", listOf("#브런치 맛집", "#햇살 좋은", "#루프탑"), "5"),
                                Triple("XXXXX 카페", listOf("#인테리어 감성", "#빈티지 소품", "#셀카 맛집"), "9"),
                                Triple("XXXXX 카페", listOf("#콘센트 많음", "#스터디룸 있음", "#조용한"), "2"),
                            )

                            cafeList.forEach { (title, tags, count) ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.Gray)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                        tags.forEach {
                                            Text(text = it, fontSize = 12.sp, color = Color.DarkGray)
                                        }
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(count, fontSize = 12.sp, color = Color.DarkGray)
                                        IconButton(onClick = { }) {
                                            Icon(Icons.Default.Add, contentDescription = "Save", tint = brown)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    "맞춤형" -> {
                        PersonalizedQuestionStack()
                    }


                    "요즘 핫한" -> {
                        HotNowScreen()


                    }
                }
            }
        }
    }
}



@Composable
fun PersonalizedQuestionStack() {
    val questionList = listOf(
        Question("😊", "오늘의 기분은 어떠세요? :)"),
        Question("☕", "오늘 뭐하실 예정이세요?"),
        Question("☁️", "오늘의 날씨는 어때요?"),
        Question("👫", "누구와 함께 가시나요?"),
        Question("❓", "참고할만한 사항이 있을까요?")
    )

    val answers = remember { mutableStateListOf<String>() }

    Column {
        for (i in questionList.indices) {
            if (i == 0 || answers.size >= i) {
                val question = questionList[i]
                var input by remember { mutableStateOf("") }

                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8E3B6))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = question.emoji,
                                fontSize = 28.sp,
                                modifier = Modifier.padding(end = 12.dp)
                            )
                            Text(
                                text = question.question,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }

                        if (answers.size > i) {
                            Text(
                                text = answers[i],
                                fontSize = 14.sp,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        } else {
                            OutlinedTextField(
                                value = input,
                                onValueChange = { input = it },
                                placeholder = { Text(question.hint) },
                                singleLine = true,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )

                            Button(
                                onClick = {
                                    if (input.isNotBlank()) {
                                        answers.add(input)
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(top = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF834D1E),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("다음")
                            }

                        }
                    }
                }
            }
        }
    }
}


@Composable
fun HotNowScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // 상단
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF834D1E)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Best seller of the week", color = Color.White, fontSize = 14.sp)
                    Text(
                        "Iced Coffee\nSweet Heaven",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text("More info ➔", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                }
                Image(
                    painter = painterResource(id = R.drawable.img_cafe_sample),
                    contentDescription = "Iced Coffee",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // 그다음
        Text(
            "This week’s recommendations",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        LazyRow {
            items(recommendationList) { item ->
                RecommendationCard(item)
            }

        }


        // 그 다음
        Text(
            "What’s in the shop?",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_cafe_sample3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
        }

        // 그다음
        Text(
            "A few words from us",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        LazyRow {
            items(cardInfoList) { card ->
                InfoCard(card)
            }
        }
    }
}
