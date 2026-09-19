package com.venky.empinfo.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit
                   ,onLoginClick: () -> Unit) {
    var  empId by rememberSaveable() { mutableStateOf("") }
    var  name by rememberSaveable() { mutableStateOf("") }
   var  email by rememberSaveable() { mutableStateOf("") }
    var  password by rememberSaveable() { mutableStateOf("") }
    var  confirmPassword by rememberSaveable() { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Register") } )
    }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            ,horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "Create Account",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
     Spacer(modifier = Modifier.height(8.dp))

      Text(text = "Register your employee account")

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(value = empId,
                onValueChange = {empId = it},
                label = {Text("Employee Id")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = name,
                onValueChange = { name = it},
                label = { Text("Employee Name")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = email,
                onValueChange = {email = it},
                label = {Text("Employee Email")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = password,
                onValueChange = {password = it},
                label = {Text("Password")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = confirmPassword,
                onValueChange = {confirmPassword = it},
                label = {Text("Confirm Password")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                if(empId.isNotBlank()
                    && name.isNotBlank()
                    && email.isNotBlank()
                    && password.isNotBlank()
                    && confirmPassword.isNotBlank()){
                    if(password == confirmPassword){
                        onRegisterSuccess()
                    }
                }
            },
                modifier = Modifier.fillMaxWidth()
                    .height(52.dp)) {
                Text(text = "Register")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Already have an account?")
                TextButton(onClick = onLoginClick) {
                    Text(text = "Login")
                }

            }
        }

    }

}
@Preview
@Composable
fun RegisterPreview() {
    RegisterScreen(onRegisterSuccess = {},
        onLoginClick = {})
}