package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

//import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var tvin:TextView?=null
    var lastnumeric: Boolean= false
    var lastdot: Boolean=false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvin=findViewById(R.id.tvinput)




    }

    fun onDigit(view: View) {
        tvin?.append((view as Button).text)
        lastnumeric=true
    }

    fun onClear(view: View) {
        tvin?.text=""
    }


    fun onDecimal(view: View) {
        if (lastnumeric&& !lastdot){
            tvin?.append(".")
            lastnumeric=false
            lastdot=true
        }
    }
    fun onOperator(view: View){
        tvin?.text?.let{

            if (lastnumeric && !isOperatorAdded(it.toString())) {
            tvin?.append((view as Button).text)
                lastnumeric=false
                lastdot=false
            }
        }
    }
    private fun isOperatorAdded(value : String) : Boolean {
        if(value.startsWith("-")){
        return false
    }
    return value.contains("/")||value.contains("+")||value.contains("-")
    }

    fun removezero(result:String):String{
        var value=result
        if(value.contains(".0")){
            value=value.substring(0,result.length-2)
        }
        return  value
    }
fun onEqual(view: View){
    if (lastnumeric){
        var tvVal=tvin?.text.toString()
        var pre=""
        try {
            if (tvVal.startsWith("-")){
                pre="-"
                tvVal=tvVal.substring(1)

            }
           if (tvVal.contains("-")) {
                val num = tvVal.split("-")

                var one = num[0]
                var two = num[1]
                if (pre.isNotEmpty()) {
                    one = pre + one
                }
                tvin?.text = removezero((one.toDouble() - two.toDouble()).toString())

            }

    else if (tvVal.contains("+")) {
                val num = tvVal.split("+")

                var one = num[0]
                var two = num[1]
                if (pre.isNotEmpty()) {
                    one = pre + one
                }
                tvin?.text = removezero((one.toDouble() + two.toDouble()).toString())

            }


    else if (tvVal.contains("*")) {
                val num = tvVal.split("*")

                var one = num[0]
                var two = num[1]
                if (pre.isNotEmpty()) {
                    one = pre + one
                }
                tvin?.text = removezero((one.toDouble() * two.toDouble()).toString())

            }


    else if (tvVal.contains("/")) {
                val num = tvVal.split("/")

                var one = num[0]
                var two = num[1]
                if (pre.isNotEmpty()) {
                    one = pre + one
                }
                tvin?.text = removezero((one.toDouble() / two.toDouble()).toString())

            }
        }catch (e:ArithmeticException){
            e.printStackTrace()
        }
    }
}

}