package com.example.flightcompose.ui.theme.screens

import android.text.Layout.Alignment
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightcompose.ui.theme.model.CategoriesData
import com.example.flightcompose.ui.theme.model.CategoriesDataResponse
import com.example.flightcompose.ui.theme.model.OffersData
import com.example.flightcompose.ui.theme.model.OffersDataProvider
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Home(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Heading()
            Categories()
            BestOffers()
        }
    }
}

@Composable
fun Heading(){
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Creating a Canvas to draw a Circle
            Canvas(modifier = Modifier
                .width(100.dp)
                .height(100.dp)) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawCircle(
                    color = Color(0xFF4cc9f0),
                    center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                    radius = size.minDimension/4,
                )
            }
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Flight", tint = Color.Black, modifier = Modifier
                .padding(top = 25.dp)
                .padding(end = 30.dp)
                .size(35.dp))
        }
        Text(text = "Hi, Jessica Doe", fontSize = 30.sp, fontWeight = FontWeight.Bold,color = Color.Black, modifier = Modifier.padding(start = 20.dp))
        Text(text = "where you will go?", fontSize = 24.sp, color = Color.Black,modifier = Modifier.padding(start = 20.dp))
        Row (
            modifier = Modifier.padding(top = 15.dp)
        ){
            val inputValue = remember { mutableStateOf(TextFieldValue()) }
            val shape = remember { RoundedCornerShape(20.dp) }

            TextField(
                value = inputValue.value,
                onValueChange = { inputValue.value = it },
                placeholder = { Text(text = "Search Locations", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.W400)},
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 20.dp)
                    .fillMaxWidth()
                    .clip(shape = shape),
                leadingIcon = { Icon(imageVector = Icons.Default.LocationOn, tint = Color.Black,contentDescription = "Location",
                    modifier = Modifier.size(30.dp)
                )}
            )
        }
    }
}

@Composable
fun Categories(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp)) {
        Text(text = "Categories", fontSize = 23.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(20.dp))
        val  dataList= remember{CategoriesDataResponse.categoriesList}
        LazyRow(){
            items(
                dataList
            ){
                CategoriesCard(data = it)
            }
        }
    }
}

@Composable
fun BestOffers(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp)) {
        Text(text = "Best Offers", fontSize = 23.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(20.dp))
        val  offersDataList= remember{OffersDataProvider.offersDataList}
        LazyRow(){
            items(
                offersDataList
            ){
                BestOffersCard(offersData = it)
            }
        }
    }
}

@Composable
fun CategoriesCard(data : CategoriesData){
    Column () {
        Box (modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .width(80.dp)
            .background(Color.White)){
            Image(painter = painterResource(id = data.image), contentDescription = "flight", modifier = Modifier.size(75.dp))
            Text(text = data.name, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 80.dp))
        }
    }
}

@Composable
fun BestOffersCard(offersData : OffersData){
    Column () {
        Box (modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(Color.White)){
            Image(painter = painterResource(id = offersData.image), contentDescription = "flight", modifier = Modifier
                .width(300.dp)
                .height(250.dp), contentScale = ContentScale.FillWidth)
            Text(text = offersData.name, textAlign = TextAlign.Start, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
            Text(text = offersData.trip, textAlign = TextAlign.Start, color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp, top = 40.dp))
            Button(onClick ={}, modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(start = 10.dp).padding(top = 130.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                Text(text = offersData.btn_text, color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        Surface(Modifier.fillMaxSize()) {
           Home()
        }
    }