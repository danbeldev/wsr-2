// Danila 04.03.2025

package com.example.wsr2.presentation.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wsr2.R
import com.example.wsr2.domain.UseCases
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }

    LoginScreen(
        isLoading = isLoading,
        onError = { email, password ->
            try {
                isLoading.value = true
                UseCases.requestLogin(
                    email = email,
                    login = null,
                    password = password,
                    onSuccess = {
                        context.getSharedPreferences("user", Context.MODE_PRIVATE)
                            .edit()
                            .putString("userId", it)
                            .apply()

                        navController.navigate("main")
                    }
                )
                "Invalid email or password"
            }catch (e: Exception) {
                "Error or network error"
            }finally {
                isLoading.value = false
            }
        }
    )
}

@Composable
fun LoginScreen(
    isLoading: MutableState<Boolean> = mutableStateOf(false),
    onError: suspend (email: String, password: String) -> String?
) {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var pasVis by remember { mutableStateOf(true) }
    var message by remember { mutableStateOf("") }

    Box(Modifier
        .fillMaxSize()
        .background(Color.White))

    if (message.isNotEmpty()) {
        AlertDialog(
            containerColor = Color.White,
            shape = AbsoluteRoundedCornerShape(15.dp),
            title = {
                Text(
                    text = "Ошибка"
                )
            },
            text = {
                Text(
                    text = message
                )
            },
            onDismissRequest = {
                message = ""
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.height(50.dp),
                        shape = AbsoluteRoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD8D8D8)
                        ),
                        onClick = {

                        }
                    ) {
                        Text(text = "Отмена", color = Color.Black)
                    }

                    Button(
                        modifier = Modifier.height(50.dp),
                        shape = AbsoluteRoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF48B2E7)
                        ),
                        onClick = {

                        }
                    ) {
                        Text(text = "OK", color = Color.White)
                    }
                }
            }
        )
    }

    Scaffold(
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Вы впервые?", color = Color(0xFF6A6A6A))
                    Spacer(Modifier.width(3.dp))
                    Text(text = "Создать пользователя", color = Color(0xFF2B2B2B))
                }

                Spacer(Modifier.height(20.dp))
            }
        },
        containerColor = Color.Transparent
    ) { p ->
        Column(
            modifier = Modifier.padding(p).fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(35.dp))

            Text(
                text = "Привет!",
                color = Color(0xFF2B2B2B),
                fontSize = 34.sp
            )

            Spacer(Modifier.height(15.dp))

            Text(
                text = "Заполните Свои данные или\nпродолжите через социальные медиа",
                color = Color(0xFF707B81),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(25.dp))

            Column {
                Text(text = "Email", color = Color(0xFF2B2B2B))

                Spacer(Modifier.height(5.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    shape = AbsoluteRoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF7F7F9),
                        unfocusedContainerColor = Color(0xFFF7F7F9),
                        focusedTextColor = Color(0xFF6A6A6A),
                        unfocusedTextColor = Color(0xFF6A6A6A),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
            }

            Spacer(Modifier.height(20.dp))

            Column {
                Text(text = "Пароль", color = Color(0xFF2B2B2B))

                Spacer(Modifier.height(5.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    shape = AbsoluteRoundedCornerShape(15.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF7F7F9),
                        unfocusedContainerColor = Color(0xFFF7F7F9),
                        focusedTextColor = Color(0xFF6A6A6A),
                        unfocusedTextColor = Color(0xFF6A6A6A),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    visualTransformation = if (pasVis) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                pasVis = !pasVis
                            }
                        ) {
                            Image(
                                painter = painterResource(R.drawable.pass),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                )
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = "Воcстановить")
            }

            Spacer(Modifier.height(25.dp))

            if (isLoading.value) {
                CircularProgressIndicator()
            }else {
                Button(
                    modifier = Modifier.fillMaxWidth().height(60.dp).testTag("login"),
                    onClick = {
                        scope.launch {
                            message = onError(email, password) ?: ""
                        }
                    },
                    shape = AbsoluteRoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF48B2E7)
                    )
                ) {
                    Text(
                        text = "Войти",
                        color = Color(0xFFF7F7F9)
                    )
                }
            }
        }
    }
}