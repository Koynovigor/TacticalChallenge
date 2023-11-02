package com.leonikl.tacticalchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leonikl.tacticalchallenge.ui.theme.Green50
import com.leonikl.tacticalchallenge.ui.theme.Red50
import com.leonikl.tacticalchallenge.ui.theme.TacticalChallengeTheme

class MainActivity : ComponentActivity() {

    private val model = MyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                model = model
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen(
    model: MyViewModel
){
    var win by remember {
        mutableStateOf(win(model))
    }
    var textScreen1 by remember {
        mutableStateOf("Player 1")
    }
    var textScreen2 by remember {
        mutableStateOf("Player 2")
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.back),
                contentScale = ContentScale.Crop
            ),
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        BasicTextField(
                            modifier = Modifier
                                .padding(5.dp)
                                .width(65.dp),
                            textStyle = TextStyle(
                                fontSize = 18.sp
                            ),
                            value = textScreen1,
                            onValueChange = { text ->
                                textScreen1 = text
                            },
                        )
                        if (win(model) == true){
                            Text(
                                text = " - Win",
                                fontSize = 18.sp
                            )
                            end(model)
                        }
                    }

                },
                modifier = Modifier
                    .height((LocalConfiguration.current.screenHeightDp/10).dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = if (model.who) Green50
                        else Color.Transparent
                )
            )
        },
        bottomBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        BasicTextField(
                            modifier = Modifier
                                .padding(5.dp)
                                .width(65.dp),
                            textStyle = TextStyle(
                                fontSize = 18.sp
                            ),
                            value = textScreen2,
                            onValueChange = { text ->
                                textScreen2 = text
                            }
                        )
                        if (win(model) == false){
                            Text(
                                text = " - Win",
                                fontSize = 18.sp,
                            )
                            end(model)
                        }
                    }

                },
                modifier = Modifier
                    .height((LocalConfiguration.current.screenHeightDp/10).dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = if (model.who) Color.Transparent
                    else Red50
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row {
                    Button(
                        onClick = {
                            val a = model.who
                            model.state1 = a
                            model.who = !model.who
                            model.enable1 = true
                            win = win(model)
                            model.enable11 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable1) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable11,
                    ) {
                        if (model.enable1){
                            Icon(
                                painter = if (model.state1){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state1) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Button(
                        onClick = {
                            val a = model.who
                            model.state2 = a
                            model.who = !model.who
                            model.enable2 = true
                            model.enable12 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable2) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable12
                    ) {
                        if (model.enable2){
                            Icon(
                                painter = if (model.state2){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state2) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Button(
                        onClick = {
                            val a = model.who
                            model.state3 = a
                            model.who = !model.who
                            model.enable3 = true
                            model.enable13 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable3) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable13
                    ) {
                        if (model.enable3){
                            Icon(
                                painter = if (model.state3){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state3) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
                Row {
                    Button(
                        onClick = {
                            val a = model.who
                            model.state4 = a
                            model.who = !model.who
                            model.enable4 = true
                            model.enable14 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable4) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable14
                    ) {
                        if (model.enable4){
                            Icon(
                                painter = if (model.state4){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state4) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Button(
                        onClick = {
                            val a = model.who
                            model.state5 = a
                            model.who = !model.who
                            model.enable5 = true
                            model.enable15 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable5) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable15
                    ) {
                        if (model.enable5){
                            Icon(
                                painter = if (model.state5){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state5) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Button(
                        onClick = {
                            val a = model.who
                            model.state6 = a
                            model.who = !model.who
                            model.enable6 = true
                            model.enable16 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable6) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable16
                    ) {
                        if (model.enable6){
                            Icon(
                                painter = if (model.state6){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state6) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
                Row {
                    Button(
                        onClick = {
                            val a = model.who
                            model.state7 = a
                            model.who = !model.who
                            model.enable7 = true
                            model.enable17 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable7) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable17
                    ) {
                        if (model.enable7){
                            Icon(
                                painter = if (model.state7){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state7) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Button(
                        onClick = {
                            val a = model.who
                            model.state8 = a
                            model.who = !model.who
                            model.enable8 = true
                            model.enable18 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable8) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable18
                    ) {
                        if (model.enable8){
                            Icon(
                                painter = if (model.state8){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state8) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Button(
                        onClick = {
                            val a = model.who
                            model.state9 = a
                            model.who = !model.who
                            model.enable9 = true
                            model.enable19 = false
                        },
                        modifier = Modifier
                            .size((LocalConfiguration.current.screenWidthDp / 7 * 2).dp)
                            .paint(
                                painter = painterResource(id = R.drawable.circl),
                                contentScale = ContentScale.Crop,
                                alpha = if (model.enable9) 0.0f
                                else 1.0f
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        enabled = model.enable19
                    ) {
                        if (model.enable9){
                            Icon(
                                painter = if (model.state9){
                                    painterResource(id = R.drawable.outline_circle_24)
                                }
                                else {
                                    painterResource(id = R.drawable.round_close_24)
                                },
                                contentDescription = "tictac",
                                tint = if (model.state9) Color.Green
                                else Color.Red,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            model.restart =
                model.enable11 || model.enable12 || model.enable13
                        || model.enable14 || model.enable15 || model.enable16
                        || model.enable17 || model.enable18 || model.enable19
            if (!model.restart){
                FloatingActionButton(
                    onClick = {
                        restart(model)
                    },
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .size((LocalConfiguration.current.screenHeightDp / 10).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bbuton),
                        contentDescription = "im",
                        contentScale = ContentScale.Crop
                    )
                    Text(text = "Restart")
                }
            }
        }
    )
}

fun restart(
    model: MyViewModel
){
    model.enable11 = true
    model.enable12 = true
    model.enable13 = true
    model.enable14 = true
    model.enable15 = true
    model.enable16 = true
    model.enable17 = true
    model.enable18 = true
    model.enable19 = true
    model.enable1 = false
    model.enable2 = false
    model.enable3 = false
    model.enable4 = false
    model.enable5 = false
    model.enable6 = false
    model.enable7 = false
    model.enable8 = false
    model.enable9 = false
    model.who = false
}

fun win(
    model: MyViewModel
): Boolean?{
    if (model.enable1 && model.enable2 && model.enable3){
        if (
            model.state1 && model.state2 && model.state3
        ){
            return true
        }
        if (
            !(model.state1 || model.state2 || model.state3)
        ){
            return false
        }
    }
    if (model.enable4 && model.enable5 && model.enable6){
        if (
            model.state4 && model.state5 && model.state6
        ){
            return true
        }
        if (
            !(model.state4 || model.state5 || model.state6)
        ){
            return false
        }
    }
    if (model.enable7 && model.enable8 && model.enable9){
        if (
            model.state7 && model.state8 && model.state9
        ){
            return true
        }
        if (
            !(model.state7 || model.state8 || model.state9)
        ){
            return false
        }
    }
    if (model.enable1 && model.enable4 && model.enable7){
        if (
            model.state1 && model.state4 && model.state7
        ){
            return true
        }
        if (
            !(model.state1|| model.state4 || model.state7)
        ){
            return false
        }
    }
    if (model.enable2 && model.enable5 && model.enable8){
        if (
            model.state2 && model.state5 && model.state8
        ){
            return true
        }
        if (
            !(model.state2 || model.state5 || model.state8)
        ){
            return false
        }
    }
    if (model.enable3 && model.enable6 && model.enable9){
        if (
            model.state3 && model.state6 && model.state9
        ){
            return true
        }
        if (
            !(model.state3 || model.state6 || model.state9)
        ){
            return false
        }
    }
    if (model.enable7 && model.enable5 && model.enable3){
        if (
            model.state7 && model.state5 && model.state3
        ){
            return true
        }
        if (
            !(model.state7 || model.state5 || model.state3)
        ){
            return false
        }
    }
    if (model.enable1 && model.enable5 && model.enable9){
        if (
            model.state1 && model.state5 && model.state9
        ){
            return true
        }
        if (
            !(model.state1 || model.state5 || model.state9)
        ){
            return false
        }
    }

    return null
}

fun end(
    model: MyViewModel
){
    model.enable11 = false
    model.enable12 = false
    model.enable13 = false
    model.enable14 = false
    model.enable15 = false
    model.enable16 = false
    model.enable17 = false
    model.enable18 = false
    model.enable19 = false
    model.who = !model.who
}