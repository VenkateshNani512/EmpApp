package com.venky.empinfo.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venky.empinfo.core.theme.EmpInfoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Loginscreen(onLoginSuccess:() -> Unit) {

  var empid by rememberSaveable { mutableStateOf("")  }
    var password by rememberSaveable { mutableStateOf("")  }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Login",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary)
        })
    }) { paddingValues ->
        Column(
            Modifier.fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center){
            OutlinedTextField(value = empid,
                label = { Text(text = "Emp Id") },
                onValueChange = {empid = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = password,
            onValueChange = {password = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation())

            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                if(empid.isNotEmpty() && password.isNotEmpty())
                    onLoginSuccess()
            }){
                Text(text = "Login")
            }

        }
    }

}
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    EmpInfoTheme {
        Loginscreen({})
    }
}