package com.taymiyyah.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.taymiyyah.beautytry.and
import com.taymiyyah.beautytry.onAlways
import com.taymiyyah.beautytry.onFailed
import com.taymiyyah.beautytry.tries
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tries {
            println("tries")
            throw RuntimeException()
        }.and {
            println("and: this block will execute even tries or any and{} throws")
        }.and {
            println("and: this block will execute even tries or any and{} throws")
        }.onFailed {
            println("onFailed: this block will execute  if any and{} or ties throws $it")
        }.onAlways {
            println("onAlways: this block will Always execute")
            throw RuntimeException()
            println("all blocks are safe and never crash")
        }


        tries {
            getWeatherF()
        }.and {
            getWeatherC()
        }.onFailed {
            logException()
        }.onAlways {
            updateTheView()
        }


        tries {
            getWeatherF()
        }


        try {
            getWeatherF()
        } catch (e: Exception) {

        }

    }

    private fun getWeatherC() {
        TODO("Not yet implemented")
    }

    private fun updateTheView() {
        TODO("Not yet implemented")
    }

    private fun logException() {
        TODO("Not yet implemented")
    }

    private fun getWeatherF() {
        TODO("Not yet implemented")
    }
}