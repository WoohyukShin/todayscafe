package com.example.project1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.project1.R
import com.example.project1.model.CafeInfo
import com.example.project1.model.CafeList
import com.example.project1.model.User
import coil3.compose.AsyncImage

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text

@Composable
fun MyCafeListScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableStateOf("내 카페리스트") }
    Scaffold(
        topBar = {
            TopTabs(navController = navController) {}
        },
        bottomBar = {
            BottomTabs(navController = navController, selectedTab) { selectedTab = it }
        }
    ){ innerPadding ->

        // test ?
        val cafeSample = CafeInfo(
            cid = "1",
            name = "cafe1",
            shortAddress = "대전 유성구 어은동",
            imageURL = R.drawable.cafeimage_example
        )
        val cafeList = CafeList(
            name = "study",
            imageURL = R.drawable.cafeimage_example,
            list = listOf(cafeSample, cafeSample, cafeSample, cafeSample, cafeSample)
        )
        val user = User(
            uid = "1",
            name = "우혁",
            followers = emptyList(),
            recommendation = listOf(cafeSample, cafeSample, cafeSample, cafeSample, cafeSample),
            cafeLists = listOf(cafeList, cafeList, cafeList)
        )


        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(colorResource(R.color.beige))
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "내 카페리스트 페이지", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1.0f))
            }
            Spacer(modifier = Modifier.height(12.dp))
            user.cafeLists.forEach{ cafeList->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { /* TODO: 상세 페이지 이동 or 수정 */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = cafeList.imageURL,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1.0f)) {
                        Text(text = cafeList.name, fontWeight = FontWeight.SemiBold)
                        Text(
                            text = "${cafeList.list.size}개의 카페",
                            fontSize = 12.sp,
                            color = colorResource(R.color.gray)
                        )
                    }
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_leaf),
                                contentDescription = "More options"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("수정") },
                                onClick = {
                                    expanded = false
                                    // TODO: 수정 처리
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("이름 변경") },
                                onClick = {
                                    expanded = false
                                    // TODO: 이름 변경 처리
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("삭제") },
                                onClick = {
                                    expanded = false
                                    // TODO: 삭제 처리
                                }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "내가 구독한 카페", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            user.recommendation.forEach { cafeInfo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { /* TODO: 상세보기로 이동 */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = cafeInfo.imageURL,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(text = cafeInfo.name, fontWeight = FontWeight.SemiBold)
                        Text(text = cafeInfo.shortAddress, fontSize = 12.sp, color = colorResource(R.color.gray))
                    }
                }
            }
        }
    }
}
