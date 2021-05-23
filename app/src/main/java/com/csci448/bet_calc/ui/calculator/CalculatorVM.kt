package com.csci448.bet_calc.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorVM : ViewModel() {
    var oddsA: Double = 0.0
    var oddsB: Double = 0.0
    var wagerA: Double = 0.0
}