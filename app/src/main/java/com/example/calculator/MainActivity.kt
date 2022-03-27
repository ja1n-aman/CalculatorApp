package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {


    private var tvDigit: TextView? = null
    var number : Boolean = false
    var point : Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDigit = findViewById(R.id.tvDigit)

    }

    fun onDigit(view: View){
        tvDigit?.append((view as Button).text)
        number=true
    }

    fun onClr(view: View){
        tvDigit?.text=""
    }

    fun onPoint(view: View){
        if(!point && number){
            tvDigit?.append(".")
            point=true
        }
    }

    fun onOperator(view: View){

        tvDigit?.text?.let{
            if(number && !isOperatorAdded(it.toString())){
                tvDigit?.append((view as Button).text)
                point=false
                number=false
            }
        }

    }

    fun isOperatorAdded(value:String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{value.contains("/") ||
                value.contains("*") ||
                value.contains("+") ||
                value.contains("-")
        }
    }

    private fun removeZeroAfterDot(result : String) : String{
        var value = result
        if(value.contains(".0"))
            value = value.substring(0,result.length-2)
        return value
    }
    fun onEqual(view: View){
        if(number){
            var tvValue = tvDigit?.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitview = tvValue.split("-")

                    var one = splitview[0]
                    var two = splitview[1]

                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvDigit?.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitview = tvValue.split("/")

                    var one = splitview[0]
                    var two = splitview[1]

                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvDigit?.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitview = tvValue.split("+")

                    var one = splitview[0]
                    var two = splitview[1]

                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvDigit?.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }else{
                    val splitview = tvValue.split("*")

                    var one = splitview[0]
                    var two = splitview[1]

                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvDigit?.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }
            }catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}


