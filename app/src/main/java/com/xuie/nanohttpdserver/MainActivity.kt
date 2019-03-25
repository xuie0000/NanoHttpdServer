package com.xuie.nanohttpdserver

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var isStarted: Boolean = false
    private lateinit var androidWebServer: AndroidWebServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        androidWebServer = AndroidWebServer()

        val btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            when {
                isStarted -> {
                    stopAndroidWebServer()
                    btnStart.text = "开始"
                    Toast.makeText(this, "停止了", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    btnStart.text = "停止"
                    startAndroidWebServer()
                    Toast.makeText(this, "开始了", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //region Start And Stop AndroidWebServer
    private fun startAndroidWebServer(): Boolean {
        if (!isStarted) {
            androidWebServer.start()
            isStarted = true
            return true
        }
        return false
    }

    private fun stopAndroidWebServer(): Boolean {
        if (isStarted) {
            androidWebServer.stop()
        }
        isStarted = false
        return true
    }
}
