package com.leonikl.tacticalchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {

    var state1 by mutableStateOf(false)
    var enable1 by mutableStateOf(false)
    var enable11 by mutableStateOf(true)

    var state2 by mutableStateOf(false)
    var enable2 by mutableStateOf(false)
    var enable12 by mutableStateOf(true)

    var state3 by mutableStateOf(true)
    var enable3 by mutableStateOf(false)
    var enable13 by mutableStateOf(true)

    var state4 by mutableStateOf(false)
    var enable4 by mutableStateOf(false)
    var enable14 by mutableStateOf(true)

    var state5 by mutableStateOf(true)
    var enable5 by mutableStateOf(false)
    var enable15 by mutableStateOf(true)

    var state6 by mutableStateOf(true)
    var enable6 by mutableStateOf(false)
    var enable16 by mutableStateOf(true)

    var state7 by mutableStateOf(true)
    var enable7 by mutableStateOf(false)
    var enable17 by mutableStateOf(true)

    var state8 by mutableStateOf(false)
    var enable8 by mutableStateOf(false)
    var enable18 by mutableStateOf(true)

    var state9 by mutableStateOf(true)
    var enable9 by mutableStateOf(false)
    var enable19 by mutableStateOf(true)


    var restart by mutableStateOf(false)

    var who by mutableStateOf(false)
}

