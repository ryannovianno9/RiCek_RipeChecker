package com.RipeChecker.RiCek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.RipeChecker.RiCek.ui.theme.Purple40
import com.RipeChecker.RiCek.ui.theme.RiCekTheme

class HomeCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RiCekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home(Modifier)
                }
            }
        }
    }
}

@Composable
fun Home(modifier: Modifier = Modifier.fillMaxSize()) {
Box(

    modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(Purple40)

)
    IconButton(onClick = { }, modifier = Modifier
        .padding(start = 20.dp)
        .size(60.dp)) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "Show more",
            modifier = Modifier
                .size(40.dp)
        )
    }
    Text(text = "Home", modifier = Modifier)

    
}

