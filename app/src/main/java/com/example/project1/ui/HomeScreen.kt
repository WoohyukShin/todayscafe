package com.example.project1.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.draw.clip

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,  // ✅ modifier 인자 추가
    userName: String = "지영"
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // 🔹 상단 헤더
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 로고 대체 박스
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFFFE8EC), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("로고", fontSize = 12.sp, color = Color.DarkGray)
            }

            Text(
                text = "홈",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // 메뉴 아이콘 대체 박스
            Box(
                modifier = Modifier
                    .size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("≡", fontSize = 20.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 태그
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Tag("Sleep")
            Tag("Inner Peace", selected = true)
            Tag("Stress")
            Tag("Anxiety")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 메인 추천 카드
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE3D3)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "$userName 님, 오늘 이런 카페는 어때요?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("20 min", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 이미지 대신 대체 박스
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("이미지 없음", color = Color.DarkGray)
                }
            }
        }
    }
}

@Composable
fun Tag(label: String, selected: Boolean = false) {
    Box(
        modifier = Modifier
            .background(
                color = if (selected) Color(0xFF393939) else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                BorderStroke(1.dp, if (selected) Color.Transparent else Color(0xFF393939)),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = if (selected) Color.White else Color(0xFF393939),
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.ExtraBold else FontWeight.Normal
        )
    }
}
