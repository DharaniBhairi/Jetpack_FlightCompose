package com.example.flightcompose.ui.theme.screens

import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.example.flightcompose.R
import com.example.flightcompose.ui.theme.model.ChipData
import com.example.flightcompose.ui.theme.model.getAllChips
import com.example.flightcompose.ui.theme.model.getData

@Composable
fun Flight(){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    color = MaterialTheme.colors.background) {
        Column {
            TopImage()
            ChipGroup()
        }
    }
}

@Composable
fun TopImage(){
    Column {
        Box {
            val image = painterResource(id = R.drawable.flight_bg)
            val backIcon = painterResource(id = R.drawable.back_arrow)
            Image(painter = image, contentDescription = null, modifier = Modifier
                .width(400.dp)
                .height(180.dp), contentScale = ContentScale.FillBounds)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp)
                    .padding(top = 20.dp)
                    .padding(end = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(painter = backIcon, contentDescription = null,modifier = Modifier.size(30.dp))
                Text(text = "Flight", fontSize = 26.sp, color = Color.Black)
                Icon(imageVector = Icons.Default.Menu, contentDescription = null, modifier = Modifier.size(30.dp))
            }
            Row {
                
            }
        }
    }
}

@Composable
fun Chip(
    trip : String = "Chip",
    isSelected : Boolean = false,
    onSelectionChanged : (String) -> Unit = {}
){
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.LightGray else Color.Blue,
        contentColor = if (isSelected) Color.Black else Color.White
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(trip)
                }
            )
        ) {
            Text(
                text = trip,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ChipGroup(
    chips: List<ChipData> = getAllChips(),
    selectedChip: ChipData? = null,
    onSelectedChanged: (String) -> Unit = {},
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            items(chips) {
                Chip(
                    trip = it.value,
                    isSelected = selectedChip == it,
                    onSelectionChanged = {
                        selectedChip?.value = getData(it).toString()
                    },
                )
            }
        }
    }
}