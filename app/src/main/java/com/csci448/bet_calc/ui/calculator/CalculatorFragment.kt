package com.csci448.bet_calc.ui.calculator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.csci448.bet_calc.R
import com.csci448.bet_calc.databinding.FragmentHomeBinding
import com.csci448.bet_calc.utility.ArbCalculator
import java.lang.Exception
import kotlin.math.absoluteValue

class CalculatorFragment : Fragment() {

    private lateinit var calculatorVM: CalculatorVM

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        calculatorVM = ViewModelProvider(this).get(CalculatorVM::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.oddsAIn.addTextChangedListener { oddsA ->
            calculatorVM.oddsA = oddsA.toString().toDouble().absoluteValue
            updateUI()
        }

        binding.oddsBIn.addTextChangedListener { oddsB ->
            calculatorVM.oddsB = oddsB.toString().toDouble().absoluteValue
            updateUI()
        }

        binding.wagerAIn.addTextChangedListener { wagerAIn ->
            calculatorVM.wagerA = wagerAIn.toString().toDouble().absoluteValue
            updateUI()
        }

        binding.decimalOdds.setOnClickListener { updateUI() }
        binding.freebet.setOnClickListener { updateUI() }

        updateUI()
        return root
    }

    override fun onResume() {
        super.onResume()

        //reload the past info
        binding.oddsAIn.setText(calculatorVM.oddsA.toString())
        binding.oddsBIn.setText(calculatorVM.oddsB.toString())
        binding.wagerAIn.setText(calculatorVM.wagerA.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(){
        try {
            val returnBundle = ArbCalculator.calculateArb(calculatorVM.oddsA,
                calculatorVM.oddsB,
                calculatorVM.wagerA,
                binding.decimalOdds.isChecked,
                binding.freebet.isChecked,
                requireContext())


            binding.wagerBText.text = returnBundle.getDouble("wager").toString()

            return
        }catch (e: Exception) {
            if(e.message != null){
                Log.e("calculator", "Return Bundle exception throw: " + e.message!!)
            }else{
                Log.e("calculator", "Return Bundle exception throw: No message")
            }
        }

        binding.wagerBText.text = getString(R.string.error)

    }
}