package com.stackmobile.calculadoragorjeta

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import com.stackmobile.calculadoragorjeta.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

     private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCalcular.setOnClickListener { calculateTip() }


        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }

        }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }



        fun calculateTip() {

            val stringInTextField = binding.costOfServiceEditText.text.toString()
            val cost = stringInTextField.toDoubleOrNull()

            if (cost == null) {
                println("")
                return
            }


            val selectId = binding.tipOptions.checkedRadioButtonId

                val tipPercentage = when (selectId) {
                    R.id.option_twenty_percent -> 0.20
                    R.id.option_fifteen_percent -> 0.15
                    else -> 0.10
                }
                var tip = tipPercentage * cost
                if ( binding.roundUpSwitch.isChecked) {
                    tip = ceil(tip)
                }

                val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
                binding.tipResult.text = getString(R.string.tip_amount, formattedTip)


        }

}