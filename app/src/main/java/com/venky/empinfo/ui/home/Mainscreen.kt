package com.venky.empinfo.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

val userList = listOf(
    User(1, "Venky", "venky@example.com", "", true),
    User(2, "John", "john@example.com", "", false)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar( navigationIcon = {
                IconButton(onClick = { println("clicked") }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back")
                }
            },
                title = {
                    Text(text = "Home")
                },
                actions = {
                    IconButton(
                        onClick = {
                            println("Clicked")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                })
        }
    ){ innerPadding ->

        Surface(modifier = Modifier.fillMaxSize()
            .padding(innerPadding),
            color = Color.LightGray) {
            Column(modifier = Modifier.fillMaxSize())
            {
                LazyColumn {
                    items(userList) { user ->
                        ProfileImage(user)
                    }
                }
            }


        }

    }
}

@Composable
fun ProfileImage(userProfile: User){
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight(align = Alignment.Top)
    )
    {
        Row(modifier = Modifier.fillMaxWidth()
            .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            ProfilePicture(0, userProfile.online)
            ProfileContent(userProfile.name, userProfile.online)

        }
    }
}

@Composable
fun ProfilePicture(drawableId:Int,online:Boolean) {
    Card(shape = CircleShape,
        border = BorderStroke(width = 2.dp,
            color = if(online)
                MaterialTheme.colorScheme.primary else Color.Red),
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Image(painter = painterResource(id = drawableId),
            contentDescription = "Profile Image",
            modifier = Modifier.size(72.dp))

    }

}
@Composable
fun ProfileContent(username : String,status : Boolean) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth())
    {
        Text(text = username,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleLarge)

        Text(text = if(status) "Active now" else "Offline",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(0.5f))

    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmpInfoTheme {
        MainScreen()
    }
}