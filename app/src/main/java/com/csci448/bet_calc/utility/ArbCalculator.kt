package com.csci448.bet_calc.utility

import android.os.Bundle
import java.lang.Exception

object ArbCalculator {

    fun calculateArb(oddsA: Double, oddsB: Double, wagerA: Double = 1.0, decimal_override:Boolean = false, freebet:Boolean = false): Bundle {

        var oddsAMut = oddsA
        var oddsBMut = oddsB

        if(!decimal_override){
            //Convert odds to decimal if not already in decimal
            if (oddsAMut >= 100){
                oddsAMut = convertOdds(oddsAMut)
            }
            if(oddsBMut >= 100) {
                oddsBMut = convertOdds(oddsBMut)
            }
        }

        if (oddsAMut < 1 || oddsBMut < 1) {
            throw Exception("Invalid Odds")
        }

        // Calculate winnings, second wager, and profit
        val returnBundle  = Bundle()
        if (!freebet) {
            val winnings = oddsAMut * wagerA
            returnBundle.putDouble("winnings", winnings)
            returnBundle.putDouble("wager", winnings / oddsBMut)

            return returnBundle
        }
        val winnings = (oddsAMut * wagerA) - wagerA
        returnBundle.putDouble("winnings", winnings)
        returnBundle.putDouble("wager", winnings / oddsBMut)

        return returnBundle
    }

    private fun convertOdds(odds: Double): Double{
        if (odds >= 100) {
            return (odds / 100) + 1
        }
        return 1 - (100 / odds)
    }

}