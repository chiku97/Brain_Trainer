package com.eft.braintrainer

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity() {

    var answerArray  = arrayOf(0, 0, 0, 0)
    private lateinit var textAddition : TextView
    private lateinit var textResult : TextView
    private lateinit var button1 : Button
    private lateinit var button2 : Button
    private lateinit var button3 : Button
    private lateinit var button4: Button
    lateinit var findCorrectAnswer : String
    private lateinit var textTimer : TextView

    private fun countTime(){
        object : CountDownTimer(31000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                textTimer.text = (millisUntilFinished / 1000).toString()
            }
            override fun onFinish() {
                val timesUp = AlertDialog.Builder(this@GameActivity)
                timesUp.create()
                timesUp.setTitle("TimesUp !! Try solving next One")
                timesUp.setPositiveButton("Yes"){ _: DialogInterface, i: Int ->
                    playAgain()
                }
                timesUp.setNeutralButton("No"){ _: DialogInterface, i: Int ->
                    Toast.makeText(this@GameActivity,"THank You",Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }.start()
    }


    @SuppressLint("SetTextI18n")
    private fun playAgain(){

        val num1 = (Math.random() * (21)).toInt()
        Log.d("Random", num1.toString())
        val num2 = (Math.random() * (21)).toInt()
        textAddition.text = "$num1 + $num2"

        val buttonPutAnswer = (Math.random() * (4)).toInt()

        for (i in 0..3){
            if (i == buttonPutAnswer){
                answerArray[i] = (num1+num2)
                findCorrectAnswer = i.toString()
            }else{
                var wrongAnswer = (Math.random() * (42)).toInt()
                while (wrongAnswer == num1+num2){
                    wrongAnswer = (Math.random() * (42)).toInt()
                }
                answerArray[i] = (wrongAnswer)
            }
        }

        button1.text = answerArray[0].toString()
        button2.text = answerArray[1].toString()
        button3.text = answerArray[2].toString()
        button4.text = answerArray[3].toString()

    }

    @SuppressLint("SetTextI18n")
    fun buttonClicked(v: View){
        textResult.visibility = View.VISIBLE
        if (findCorrectAnswer == v.tag){
            textResult.text = "CORRECT !"
            val d = AlertDialog.Builder(this)
            d.create()
            d.setTitle("Answer CORRECT !! Try Again")
            d.setPositiveButton("Yes") { dialog, which ->
                playAgain()
            }
            d.setNeutralButton("No") { dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(this, "Thank you", Toast.LENGTH_LONG).show()
                finish()
            }
            d.show()

        }else{
            textResult.text = "WRONG !"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
         textAddition = findViewById(R.id.textViewAddition)
         button1 = findViewById(R.id.button)
         button2 = findViewById(R.id.button2)
         button3 = findViewById(R.id.button3)
         button4 = findViewById(R.id.button4)
         textResult = findViewById(R.id.textViewResult)
         textTimer = findViewById(R.id.textViewTimer)

        countTime()
        playAgain()


    }
}