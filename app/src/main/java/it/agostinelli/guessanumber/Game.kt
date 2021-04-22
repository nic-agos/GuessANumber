package it.agostinelli.guessanumber

import android.util.Log
import kotlin.random.Random

class Game(private var maxAttempts: Int){
    private val TAG = "GAN"

    private var maxAt: Int = maxAttempts
    private var end: Int = 100

    private var toGuess: Int = 0
    private var attempts = 0

    fun getMaxAttempts() : Int {
        return this.maxAttempts
    }
    fun getAttempts(): Int{
        return this.attempts

    }
    fun startGame() {
        toGuess = Random.nextInt(end) + 1
        attempts = 0
        Log.v(TAG, "Base attempts: $attempts")
    }

    fun check(input: Int) : String {
        attempts += 1
        Log.v(TAG, "Updated attempts: $attempts")

        if (attempts >= maxAt){
            return "YOU LOOSE"
        }else{
            if (input == toGuess){
                return "YOU WIN"
            }
            if (input > toGuess){
                return "TOO BIG"
            }
        }
        return "TOO SMALL"
    }
}