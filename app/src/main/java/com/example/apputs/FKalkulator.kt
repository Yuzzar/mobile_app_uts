package com.example.apputs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.apputs.databinding.FragmentKalkulatorBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var expression:Expression

/**
 * A simple [Fragment] subclass.
 * Use the [FKalkulator.newInstance] factory method to
 * create an instance of this fragment.
 */
class   FKalkulator : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentKalkulatorBinding
    private var lastNumeric = false
    private var stateError = false
    private var lastDot = false
    private lateinit var expression: Expression

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentKalkulatorBinding.inflate(inflater, container, false)

        // Inisialisasi expression
        expression = ExpressionBuilder("0").build()

        // Tambahkan event click ke tombol-tombol di sini
        binding.btn1.setOnClickListener { onDigitClick(binding.btn1) }
        binding.btn2.setOnClickListener { onDigitClick(binding.btn2) }
        binding.btn3.setOnClickListener { onDigitClick(binding.btn3) }
        binding.btn4.setOnClickListener { onDigitClick(binding.btn4) }
        binding.btn5.setOnClickListener { onDigitClick(binding.btn5) }
        binding.btn6.setOnClickListener { onDigitClick(binding.btn6) }
        binding.btn7.setOnClickListener { onDigitClick(binding.btn7) }
        binding.btn8.setOnClickListener { onDigitClick(binding.btn8) }
        binding.btn9.setOnClickListener { onDigitClick(binding.btn9) }
        binding.btn0.setOnClickListener { onDigitClick(binding.btn0) }
        binding.btnBuka.setOnClickListener { onDigitClick(binding.btnBuka) }
        binding.btnTutup.setOnClickListener { onDigitClick(binding.btnTutup) }

        binding.btnTambah.setOnClickListener { onOperatorClick(binding.btnTambah) }
        binding.btnKurang.setOnClickListener { onOperatorClick(binding.btnKurang) }
        binding.btnKali.setOnClickListener { onOperatorClick(binding.btnKali) }
        binding.btnBagi.setOnClickListener { onOperatorClick(binding.btnBagi) }

        binding.btnClear.setOnClickListener { onAllClearClick() }
        binding.btnHapus.setOnClickListener { onBackClick() }


        binding.btnHasil.setOnClickListener { onEqualClick() }
        binding.btnTitik.setOnClickListener { onDigitClick(binding.btnTitik) }

        return binding.root
    }

    private fun onAllClearClick() {
        binding.inputKalk.text = ""
        binding.hasilInput.text = ""
        stateError = false
        lastDot = false
        lastNumeric = false
        // Don't set the visibility to View.GONE
    }


    private fun onEqualClick() {
        onEqual()
        binding.inputKalk.text = binding.hasilInput.text.toString().drop(1)
    }

    private fun onDigitClick(view: View) {
        if (stateError) {
            binding.inputKalk.text = (view as Button).text
            stateError = false
        } else {
            binding.inputKalk.append((view as Button).text)
        }
        lastNumeric = true
    }

    private fun onOperatorClick(view: View) {
        if (!stateError) {
            val operator = (view as Button).text.toString()

            when (operator) {
                "(" -> {
                    if (lastNumeric) {
                        binding.inputKalk.append(" $operator ")
                        lastNumeric = false
                        lastDot = false
                    } else {
                        binding.inputKalk.append(operator)
                    }
                }
                ")" -> {
                    if (lastNumeric) {
                        binding.inputKalk.append(" $operator ")
                        lastNumeric = false
                        lastDot = false
                        onEqual()
                    }
                }
                "x" -> {
                    binding.inputKalk.append(" * ")
                    lastNumeric = false
                    lastDot = false
                }
                ":" -> {
                    binding.inputKalk.append(" / ")
                    lastNumeric = false
                    lastDot = false
                }
                else -> {
                    binding.inputKalk.append(" $operator ")
                    lastNumeric = false
                    lastDot = false
                }
            }
        }
    }






    private fun onBackClick() {
        binding.inputKalk.text = binding.inputKalk.text.toString().dropLast(1)
        try {
            val lastChar = binding.inputKalk.text.toString().last()
            if (lastChar.isDigit()) {
                onEqual()
            }
        } catch (e: Exception) {
            binding.hasilInput.text = ""
           
            Log.e("last char error", e.toString())
        }
    }

    private fun onEqual() {
        if (lastNumeric && !stateError) {
            val txt = binding.inputKalk.text.toString().trim()

            if (txt.isNotEmpty()) {
                // Replace 'x' with '*' and ':' with '/'
                // Ganti 'X' dengan '*'
                val modifiedTxt = txt.replace("X", "*").replace(":", "/")


                // Log the expression before evaluation
                Log.d("Ekspresi", "Ekspresi sebelum evaluasi: $modifiedTxt")

                expression = ExpressionBuilder(modifiedTxt).build()

                try {
                    val result = expression.evaluate()
                    binding.hasilInput.visibility = View.VISIBLE
                    binding.hasilInput.text = "=$result"
                } catch (ex: ArithmeticException) {
                    Log.e("evaluate error", ex.toString())
                    binding.hasilInput.text = "Error"
                    stateError = true
                    lastNumeric = true
                }
            } else {
                binding.hasilInput.text = ""
                binding.hasilInput.visibility = View.GONE
            }
        }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FKalkulator.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FKalkulator().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
