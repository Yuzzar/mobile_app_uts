package com.example.apputs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.apputs.databinding.FragmentSuhuBinding
import java.text.DecimalFormat
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FSuhu.newInstance] factory method to
 * create an instance of this fragment.
 */
class FSuhu : Fragment() {
    private var binding: FragmentSuhuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuhuBinding.inflate(inflater, container, false)
        val root = binding!!.root

        val temperatureUnits = arrayOf("Celsius", "Kelvin", "Fahrenheit")
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, temperatureUnits)
        binding!!.spinnerFrom.adapter = arrayAdapter
        binding!!.spinnerTo.adapter = arrayAdapter

        binding!!.convertButton.setOnClickListener { convertTemperature() }

        return root
    }

    private fun convertTemperature() {
        val inputTemperatureText = binding!!.temperatureInput.text.toString()
        if (inputTemperatureText.isEmpty()) {
            showToast("Please enter a valid numeric value")
            return
        }

        val inputTemperature = inputTemperatureText.toDoubleOrNull()
        if (inputTemperature == null) {
            showToast("Please enter a valid numeric value")
            return
        }

        val fromUnitPosition = binding!!.spinnerFrom.selectedItemPosition
        val toUnitPosition = binding!!.spinnerTo.selectedItemPosition

        val conversionResult = calculateConversion(inputTemperature, fromUnitPosition, toUnitPosition)
        displayResult(conversionResult)
    }

    private fun calculateConversion(value: Double, fromUnit: Int, toUnit: Int): Double {
        return when (fromUnit) {
            0 -> calculateCelsiusToOther(value, toUnit) // Celsius
            1 -> calculateKelvinToOther(value, toUnit) // Kelvin
            2 -> calculateFahrenheitToOther(value, toUnit) // Fahrenheit
            else -> 0.0
        }
    }

    private fun calculateCelsiusToOther(celsius: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> celsius // Celsius
            1 -> celsius + 273.15 // Kelvin
            2 -> (celsius * 9 / 5) + 32 // Fahrenheit
            else -> 0.0
        }
    }

    private fun calculateKelvinToOther(kelvin: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> kelvin - 273.15 // Celsius
            1 -> kelvin // Kelvin
            2 -> (kelvin - 273.15) * 9 / 5 + 32 // Fahrenheit
            else -> 0.0
        }
    }

    private fun calculateFahrenheitToOther(fahrenheit: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> (fahrenheit - 32) * 5 / 9 // Celsius
            1 -> ((fahrenheit - 32) * 5 / 9) + 273.15 // Kelvin
            2 -> fahrenheit // Fahrenheit
            else -> 0.0
        }
    }

    private fun displayResult(result: Double) {
        val resultText = DecimalFormat("#.#####").format(result)
        binding!!.conversionResult.text = resultText
        binding!!.conversionResult.visibility = View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FSuhu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                FSuhu().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}