package com.gyms.kollek.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gyms.kollek.R
import com.gyms.kollek.ui.theme.Typography

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun KollekLoginScrean(errorMessage : Boolean, onLoginClicked: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    style = Typography.h1,
                    fontStyle = FontStyle.Italic,
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(3.dp)
                        .background(color = Color.Red)
                        .border(width = 4.dp, color = Color.Black),
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(20.dp)
                                .background(color = Color.Blue)
                                .border(width = 4.dp, color = Color.Black),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            TextField(
                                value = username,
                                onValueChange = { username = it },
                                label = { Text(stringResource(id = R.string.username)) }
                            )
                            TextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text(stringResource(id = R.string.password)) },
                                visualTransformation = PasswordVisualTransformation()
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = if(errorMessage) stringResource(id = R.string.error_login) else "")
                            Button(
                                modifier = Modifier.padding(vertical = 24.dp),
                                onClick = { onLoginClicked("$username | $password")}
                            ) {
                                Text(stringResource(id = R.string.login_buton))
                            }
                        }
                        /*Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(20.dp)
                                .background(color = Color.Yellow)
                                .border(width = 4.dp, color = Color.Black),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Hello")
                        }*/
                    }
                }
            }
        }
    }
}