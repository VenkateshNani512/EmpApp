package com.venky.empinfo.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel,onLoginSuccess:() -> Unit) {

  var email by rememberSaveable { mutableStateOf("")  }

    var password by rememberSaveable { mutableStateOf("")  }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

     when(uiState){
        is LoginUiState.Idle -> {
             println("Idle")
         }

         is LoginUiState.Loading -> {
             println("Loading")
         }
        is LoginUiState.Success -> {
            LaunchedEffect(Unit) {
                onLoginSuccess()
            }

         }
         is LoginUiState.Error -> {
             println("Error")
         }

     }
     Column(
            Modifier.fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center){
            Text(text = "Login",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth())
         Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(value = email,
                label = {
                    Text(text = "Email",color = Color.Black)
                        },
                onValueChange = {email = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = password,
            onValueChange = {password = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(text = "Password",color = Color.Black) },
            visualTransformation = PasswordVisualTransformation())

            Spacer(modifier = Modifier.height(30.dp))

            Button(modifier = Modifier.fillMaxWidth().height(45.dp),
                enabled = uiState !is LoginUiState.Loading,
                onClick = {
                if(email.isNotEmpty() && password.isNotEmpty())
                   viewModel.login(email,password)
            }
            )
            {
                if (uiState is LoginUiState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Login")
                }
            }

        }
    }

