package com.xuie.nanohttpdserver

import android.content.Context
import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoWSD

/**
 * @author Jie Xu
 * @date 2019/3/25
 */
class AndroidWebSocketServer(val context: Context) : NanoWSD(PORT) {
    val connections: ArrayList<WebSocket> = arrayListOf()

    companion object {
        const val PORT = 8766
    }

    override fun openWebSocket(handshake: NanoHTTPD.IHTTPSession): NanoWSD.WebSocket {
        var uri = handshake.uri
        uri = uri.replaceFirst("/", "", true)

        return WebSocket(handshake, this)
    }

    public override fun serveHttp(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {
        return NanoHTTPD.newFixedLengthResponse("Command  not found")
    }
}