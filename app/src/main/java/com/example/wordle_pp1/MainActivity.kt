package com.example.wordle_pp1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.text.TextUtils
import android.view.View


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val checkBtn = findViewById<Button>(R.id.btnCheck)
        var firstLetter = findViewById<EditText>(R.id.edt01)
        var secondLetter = findViewById<EditText>(R.id.edt02)
        var thirdLetter = findViewById<EditText>(R.id.edt03)
        var fourthLetter =findViewById<EditText>(R.id.edt04)
        val restartBtn = findViewById<Button>(R.id.btnRestart)

        var trial = 0
        var word = FourLetterWordList.getRandomFourLetterWord()
        checkBtn.setOnClickListener {
            val let1 = firstLetter.text.toString().trim()
            val let2 = secondLetter.text.toString().trim()
            val let3 = thirdLetter.text.toString().trim()
            val let4 = fourthLetter.text.toString().trim()

            if(!TextUtils.isEmpty(let1)&&
                !TextUtils.isEmpty(let2)&&
                !TextUtils.isEmpty(let3)&&
                !TextUtils.isEmpty(let4)){
                var cntr=0
                if(let1.equals(word[0].toString(), ignoreCase = true)){
                    cntr++
                    firstLetter.setBackgroundColor(Color.parseColor("#00FF00"))
                }
                else if(word.contains(let1, ignoreCase = true)){
                    firstLetter.setBackgroundColor(Color.parseColor("#FFFF00"))
                }
                if(let2.equals(word[1].toString(), ignoreCase = true)){
                    cntr++
                    secondLetter.setBackgroundColor(Color.parseColor("#00FF00"))
                }
                else if(word.contains(let2, ignoreCase = true)){
                    secondLetter.setBackgroundColor(Color.parseColor("#FFFF00"))
                }
                if(let3.equals(word[2].toString(), ignoreCase = true)){
                    cntr++
                    thirdLetter.setBackgroundColor(Color.parseColor("#00FF00"))
                }
                else if(word.contains(let3, ignoreCase = true)){
                    thirdLetter.setBackgroundColor(Color.parseColor("#FFFF00"))
                }
                if(let4.equals(word[3].toString(), ignoreCase = true)){
                    cntr++
                    fourthLetter.setBackgroundColor(Color.parseColor("#00FF00"))
                }
                else if(word.contains(let4, ignoreCase = true)){
                    fourthLetter.setBackgroundColor(Color.parseColor("#FFFF00"))
                }

                if(cntr!=4 && trial<2){
                    trial++
                    firstLetter.isEnabled = false
                    secondLetter.isEnabled = false
                    thirdLetter.isEnabled = false
                    fourthLetter.isEnabled = false
                    var temp = resources.getIdentifier("edt${trial}1", "id", packageName)
                    firstLetter = findViewById<EditText>(temp)
                    temp = resources.getIdentifier("edt${trial}2", "id", packageName)
                    secondLetter = findViewById<EditText>(temp)
                    temp = resources.getIdentifier("edt${trial}3", "id", packageName)
                    thirdLetter = findViewById<EditText>(temp)
                    temp = resources.getIdentifier("edt${trial}4", "id", packageName)
                    fourthLetter =findViewById<EditText>(temp)

                    firstLetter.visibility = View.VISIBLE
                    secondLetter.visibility = View.VISIBLE
                    thirdLetter.visibility = View.VISIBLE
                    fourthLetter.visibility = View.VISIBLE
                }

                else if(cntr==4){
                    //you got it right, display a gif
                    // disable the button
                    Toast.makeText(applicationContext, "You Got it Right! It took you ${trial} trials.", Toast.LENGTH_LONG).show()
                    findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gifCel).visibility = View.VISIBLE
                    restartBtn.visibility = View.VISIBLE
                    checkBtn.visibility = View.INVISIBLE
                }
                else{
                    Toast.makeText(applicationContext, "Oppsie, you guessed it wrong! The word was ${word}.", Toast.LENGTH_LONG).show()
                    findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gifloss).visibility = View.VISIBLE
                    restartBtn.visibility = View.VISIBLE
                    checkBtn.visibility = View.INVISIBLE
                    for (i in 1..4){
                        var temp1 = "edt2${i}"
                        var curredtName = resources.getIdentifier(temp1, "id", packageName)
                        var curredt = findViewById<EditText>(curredtName)
                        curredt.isEnabled = false
                    }
                }


            }
            else{
                Toast.makeText(applicationContext, "Please Enter All Four Letters.", Toast.LENGTH_SHORT).show()
            }

        }
        restartBtn.setOnClickListener {
            trial =0
            for (i in 1..4){
                var temp1 = "edt0${i}"
                var curredtName = resources.getIdentifier(temp1, "id", packageName)
                var curredt = findViewById<EditText>(curredtName)
                curredt.setText("")
                curredt.setBackgroundColor(Color.parseColor("#FFFFFF"))
                curredt.isEnabled = true
            }

            for (i in 1..2){
                for (j in 1..4){
                    var temp2 = "edt${i}${j}"
                    var curredtName = resources.getIdentifier(temp2, "id", packageName)
                    var curredt = findViewById<EditText>(curredtName)
                    curredt.setText("")
                    curredt.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    curredt.visibility = View.INVISIBLE
                    curredt.isEnabled = true
                }
            }
            firstLetter = findViewById<EditText>(R.id.edt01)
            secondLetter = findViewById<EditText>(R.id.edt02)
            thirdLetter = findViewById<EditText>(R.id.edt03)
            fourthLetter = findViewById<EditText>(R.id.edt04)
            word = FourLetterWordList.getRandomFourLetterWord()
            restartBtn.visibility = View.INVISIBLE
            checkBtn.visibility = View.VISIBLE
            findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gifCel).visibility = View.INVISIBLE
            findViewById<pl.droidsonroids.gif.GifImageView>(R.id.gifloss).visibility = View.INVISIBLE
        }
    }


}