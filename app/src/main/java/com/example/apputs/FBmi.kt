package com.example.apputs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apputs.databinding.FragmentBmiBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FBmi.newInstance] factory method to
 * create an instance of this fragment.
 */
class FBmi : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBmiBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBmiBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment

        binding.weightpicker.minValue = 30
        binding.weightpicker.maxValue = 150

        binding.heigpicker.minValue = 100
        binding.heigpicker.maxValue = 250

        binding.weightpicker.setOnValueChangedListener { _, _, _ ->
            calculateBmi()
        }

        binding.heigpicker.setOnValueChangedListener { _, _, _ ->
            calculateBmi()
        }

        return binding.root
    }
    private fun calculateBmi(){
        val height = binding.heigpicker.value
        val doubleheight = height.toDouble()/100

        val weight = binding.weightpicker.value

        val bmi = weight.toDouble() / (doubleheight * doubleheight)

        binding.resulstTV.text = String.format("Your BMI is: %.2f", bmi)
        binding.HealthtyTV.text = String.format("Considered: %s", healthyMessage(bmi))
    }

    private fun healthyMessage(bmi: Double): String{
        if (bmi < 18.5)
            return "Underweight"
        if(bmi < 25.0)
            return "Healthy"
        if (bmi < 30.0)
            return "Overweight"

        return "obese"
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FBmi.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                FBmi().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}