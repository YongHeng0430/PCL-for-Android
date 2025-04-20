@file:OptIn(ExperimentalMaterial3Api::class)

package com.neko.pcl2

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.animation.core.tween
import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LauncherScreen() {
    var account by remember { mutableStateOf("") }
    val uriHandler = LocalUriHandler.current

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.launcher_background),
            contentDescription = "背景图",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        var selectedTab by remember { mutableStateOf("启动") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            // 占位：顶部菜单
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color(0xFF1677FF)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "PCL II",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.padding(start = 250.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        listOf("启动", "下载", "设置", "更多").forEach { label ->
                            val isSelected = selectedTab == label
                            val backgroundColor by animateColorAsState(
                                targetValue = if (isSelected) Color.White else Color.Transparent,
                                animationSpec = tween(durationMillis = 300)
                            )
                            val textColor by animateColorAsState(
                                targetValue = if (isSelected) Color(0xFF1677FF) else Color.White,
                                animationSpec = tween(durationMillis = 300)
                            )
                            Box(
                                modifier = Modifier
                                    .background(color = backgroundColor, shape = RoundedCornerShape(50))
                                    .clickable { selectedTab = label }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = label,
                                    color = textColor,
                                    fontSize = 14.sp
                                )
                            }


                        }


                    }
                }
            }

            AnimatedVisibility(visible = selectedTab == "启动") {
                // 启动页内容
                Row(modifier = Modifier.fillMaxWidth().heightIn(min = 0.dp)) {
                    Box(
                        modifier = Modifier
                            .width(320.dp)
                            .fillMaxHeight()
                            .background(Color.White.copy(alpha = 0.9f))
                            .padding(24.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // 恢复正版/离线按钮
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1677FF)),
                                    shape = RoundedCornerShape(50),
                                    modifier = Modifier.height(36.dp).weight(1f)
                                ) {
                                    Text("正版", color = Color.White, fontSize = 14.sp)
                                }
                                OutlinedButton(
                                    onClick = {},
                                    border = BorderStroke(1.dp, Color(0xFF1677FF)),
                                    shape = RoundedCornerShape(50),
                                    modifier = Modifier.height(36.dp).weight(1f)
                                ) {
                                    Text("离线", color = Color(0xFF1677FF), fontSize = 14.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "User Icon",
                                tint = Color(0xFF1677FF),
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth().height(44.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .border(1.dp, Color(0xFFCCCCCC), RoundedCornerShape(4.dp))
                                        .clickable { }
                                        .padding(horizontal = 12.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        text = "选择账号",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                OutlinedButton(
                                    onClick = {},
                                    border = BorderStroke(1.dp, Color(0xFF1677FF)),
                                    shape = RoundedCornerShape(4.dp),
                                    contentPadding = PaddingValues(horizontal = 12.dp),
                                    modifier = Modifier.height(44.dp)
                                ) {
                                    Text("登录", color = Color(0xFF1677FF), fontSize = 14.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("» 购买正版", color = Color.Gray, fontSize = 12.sp)
                                Text("» 前往官网", color = Color.Gray, fontSize = 12.sp)
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedButton(
                                onClick = {},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, Color(0xFF1677FF))
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("下载游戏", color = Color(0xFF1677FF), fontSize = 14.sp)
                                    Text("未检测到可用的游戏版本", fontSize = 10.sp, color = Color.Gray)
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            OutlinedButton(
                                onClick = {},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                border = BorderStroke(1.dp, Color(0xFFE0E0E0))
                            ) {
                                Text("版本选择", fontSize = 14.sp)
                            }
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize()) {}
                }
            }
        }
    }

    @Composable
    fun DownloadPage(selectedTab: String) {
        AnimatedVisibility(visible = selectedTab != "启动") {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(320.dp)
                        .fillMaxHeight()
                        .background(Color.White.copy(alpha = 0.95f))
                        .padding(start = 24.dp, top = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(160.dp)
                            .fillMaxHeight()
                            .padding(top = 24.dp)
                    ) {
                        Text("Minecraft", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("🎮 游戏下载", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1677FF))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("🛠 手动安装包", fontSize = 14.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("社区资源", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("📦 Mod", fontSize = 14.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("📚 整合包", fontSize = 14.sp, color = Color.Black)
                    }
                    Box(modifier = Modifier.fillMaxSize()) {
                        // 下载右侧内容预留
                    }
                }
            }
        }
    }
}
