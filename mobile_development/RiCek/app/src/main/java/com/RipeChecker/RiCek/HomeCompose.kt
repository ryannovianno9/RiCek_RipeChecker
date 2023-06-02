package com.RipeChecker.RiCek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(modifier = Modifier) {
    Box(

    modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(Purple40)

){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Home",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp))
    }
}

    Column(
        modifier = Modifier
            .padding(top = 30.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "Halo",
            fontSize = 50.sp,
            color = Purple40,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp)
        )
        Text(
            text = "Selamat Datang",
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(top = 5.dp))
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription ="banner",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "Artikel",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
            )
            Text(text = "Selengkapnya",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier
            )
        }
        LazyColumn(contentPadding = PaddingValues(10.dp), modifier = Modifier.padding(top = 20.dp)){
            items(5) { index ->
                Text(text = "Item: $index")
            }
        }



    } }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                end = 20.dp, bottom = 20.dp
            )
    ) {
        FloatingActionButton(
            onClick = {  },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .size(100.dp)
        ) {

        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    RiCekTheme {
        Home(Modifier)
    }
}