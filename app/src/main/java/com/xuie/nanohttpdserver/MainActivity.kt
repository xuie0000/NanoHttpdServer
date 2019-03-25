package com.xuie.nanohttpdserver

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.Formatter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var isHttpStarted: Boolean = false
    private lateinit var androidHttpServer: AndroidHttpServer

    private var isWskStarted: Boolean = false
    private lateinit var androidWebSocketServer: AndroidWebSocketServer

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        androidHttpServer = AndroidHttpServer()
        androidWebSocketServer = AndroidWebSocketServer(this)

        findViewById<TextView>(R.id.tv_http_request).text = "http://" + getIpAddress() + ":8081"
        val btnHttpStart = findViewById<Button>(R.id.btn_http_start)
        btnHttpStart.setOnClickListener {
            when {
                isHttpStarted -> {
                    stopAndroidWebServer()
                    btnHttpStart.text = "开始"
                    Toast.makeText(this, "停止了", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    btnHttpStart.text = "停止"
                    startAndroidWebServer()
                    Toast.makeText(this, "开始了", Toast.LENGTH_SHORT).show()
                }
            }
        }

        findViewById<TextView>(R.id.tv_wsd_request).text = "ws://" + getIpAddress() + ":" + AndroidWebSocketServer.PORT
        val btnWsdStart = findViewById<Button>(R.id.btn_wsd_start)
        btnWsdStart.setOnClickListener {
            when {
                isWskStarted -> {
                    androidWebSocketServer.closeAllConnections()
                    btnWsdStart.text = "开始"
                    Toast.makeText(this, "停止了", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    btnWsdStart.text = "停止"
                    androidWebSocketServer.start()
                    Toast.makeText(this, "开始了", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //region Start And Stop AndroidWebServer
    private fun startAndroidWebServer(): Boolean {
        if (!isHttpStarted) {
            androidHttpServer.start()
            isHttpStarted = true
            return true
        }
        return false
    }

    private fun stopAndroidWebServer(): Boolean {
        if (isHttpStarted) {
            androidHttpServer.stop()
        }
        isHttpStarted = false
        return true
    }

    private fun getIpAddress(): String {
        val wm = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
    }
}
