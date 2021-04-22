package it.agostinelli.guessanumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import it.agostinelli.guessanumber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "GAN"
    private lateinit var binding: ActivityMainBinding
    private lateinit var game: Game


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(binding)
    }

    inner class Handler(activityMainBinding: ActivityMainBinding){
        private val myBind = activityMainBinding

        init {
            val btns = listOf(
                    myBind.btn0,
                    myBind.btn1,
                    myBind.btn2,
                    myBind.btn3,
                    myBind.btn4,
                    myBind.btn5,
                    myBind.btn6,
                    myBind.btn7,
                    myBind.btn8,
                    myBind.btn9
            )
            for (btn in btns) {
                btn.isEnabled = false
            }
            myBind.btnOk.isEnabled = false
            myBind.btnCanc.isEnabled = false

            for (btn in btns){
                btn.setOnClickListener {
                    val num : String = btn.text.toString()
                    val prev : String = myBind.tvInput.text.toString()
                    val concat = prev + num
                    myBind.tvInput.text = concat
                    Log.v(TAG, "Number to guess: $concat")
                }
            }
            myBind.btnCanc.setOnClickListener {
                val text : String = myBind.tvInput.text.toString()
                val text2 : String = text.dropLast(1)
                binding.tvInput.text = text2
                Log.v(TAG, "Canc: $text2")
            }
            myBind.btnOk.setOnClickListener {
                val text : String = myBind.tvInput.text.toString()
                val input : Int = text.toInt()

                val res : String = game.check(input)

                if (res == "YOU WIN"){
                    myBind.ivState.setImageResource(R.drawable.you_win)
                    myBind.tvResult.setText(R.string.you_win)
                    Log.v(TAG, "You win")

                    for (btn in btns) {
                        btn.isEnabled = false
                    }
                    myBind.btnOk.isEnabled = false
                    myBind.btnCanc.isEnabled = false
                    myBind.btnStart.visibility = View.VISIBLE
                    myBind.tvInput.setText(R.string.empty)
                    myBind.tvLives.setText(R.string.empty)
                }
                if(res == "YOU LOOSE"){
                    myBind.ivState.setImageResource(R.drawable.you_loose)
                    myBind.tvResult.setText(R.string.you_loose)
                    Log.v(TAG, "You loose, no more attempts")

                    for (btn in btns) {
                        btn.isEnabled = false
                    }
                    myBind.btnOk.isEnabled = false
                    myBind.btnCanc.isEnabled = false
                    myBind.btnStart.visibility = View.VISIBLE
                    myBind.tvLives.setText(R.string.empty)
                    myBind.tvInput.setText(R.string.empty)
                }
                if(res == "TOO BIG"){
                    myBind.tvLives.text = getString(R.string.rem_att) + (game.getMaxAttempts() - game.getAttempts())
                    myBind.ivState.setImageResource(R.drawable.too)
                    myBind.tvInput.setText(R.string.empty)
                    myBind.tvResult.setText(R.string.too_big)
                    Log.v(TAG, "Too big")
                }
                if(res == "TOO SMALL"){
                    myBind.tvLives.text = getString(R.string.rem_att) + (game.getMaxAttempts() - game.getAttempts())
                    myBind.ivState.setImageResource(R.drawable.too)
                    myBind.tvInput.setText(R.string.empty)
                    myBind.tvResult.setText(R.string.too_small)
                    Log.v(TAG, "Too small")

                }
            }
            myBind.btnStart.setOnClickListener {
                game = Game(10)
                game.startGame()
                myBind.tvResult.setText(R.string.empty)
                myBind.tvInput.setText(R.string.empty)
                myBind.ivState.setImageResource(R.drawable.wait)
                myBind.tvLives.text = getString(R.string.rem_att) + game.getMaxAttempts().toString()
                myBind.btnStart.visibility = View.INVISIBLE
                for (btn in btns) {
                    btn.isEnabled = true
                }
                myBind.btnOk.isEnabled = true
                myBind.btnCanc.isEnabled = true
                Log.v(TAG, "Game start")
            }
        }
    }
}