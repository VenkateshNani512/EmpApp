package com.venky.empinfo.presentation

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venky.empinfo.core.theme.EmpInfoTheme
import com.venky.empinfo.domain.model.User
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.venky.empinfo.R
import com.venky.empinfo.domain.model.Employee
import com.venky.empinfo.presentation.employee.EmployeeUiState
import com.venky.empinfo.presentation.employee.EmployeeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: EmployeeViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getEmployee()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            println("Back clicked")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                title = {
                    Text(text = "Home")
                },

                actions = {
                    IconButton(
                        onClick = {
                            println("Search clicked")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        }

    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.LightGray
        ) {

            when (val state = uiState) {

                EmployeeUiState.Idle -> {
                    // Nothing to show
                }

                EmployeeUiState.Loading -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is EmployeeUiState.Success -> {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        items(state.employees) { employee ->

                          EmployeeItem(employee)
                        }
                    }
                }

                is EmployeeUiState.Error -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = state.message
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeItem(
    employee: Employee
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Employee initial
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.primary
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = employee.name
                        .firstOrNull()
                        ?.uppercase()
                        ?: "?",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = employee.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = employee.email,
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Skills: ${employee.skills}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
//@Composable
//fun ProfileImage(userProfile: User){
//    Card(modifier = Modifier
//        .padding(16.dp)
//        .fillMaxWidth()
//        .wrapContentHeight(align = Alignment.Top)
//    )
//    {
//        Row(modifier = Modifier.fillMaxWidth()
//            .background(color = Color.White),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start) {
//            ProfilePicture(R.drawable.ic_launcher_background, userProfile.online)
//            ProfileContent(userProfile.name, userProfile.online)
//
//        }
//    }
//}
//
//@Composable
//fun ProfilePicture(drawableId:Int,online:Boolean) {
//    Card(shape = CircleShape,
//        border = BorderStroke(width = 2.dp,
//            color = if(online)
//                MaterialTheme.colorScheme.primary else Color.Red),
//        modifier = Modifier.padding(16.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ){
//        Image(painter = painterResource(id = drawableId),
//            contentDescription = "Profile Image",
//            modifier = Modifier.size(72.dp))
//
//    }
//
//}
//@Composable
//fun ProfileContent(username : String,status : Boolean) {
//    Column(modifier = Modifier
//        .padding(8.dp)
//        .fillMaxWidth())
//    {
//        Text(text = username,
//            modifier = Modifier.padding(8.dp),
//            style = MaterialTheme.typography.titleLarge)
//
//        Text(text = if(status) "Active now" else "Offline",
//            modifier = Modifier.padding(8.dp),
//            style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(0.5f))
//
//    }
//}

