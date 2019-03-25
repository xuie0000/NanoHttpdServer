package com.xuie.nanohttpdserver

import fi.iki.elonen.NanoHTTPD

/**
 * @author Jie Xu
 * @date 2019/3/22
 */
class AndroidHttpServer : NanoHTTPD(8081) {
    override fun serve(session: IHTTPSession?): Response {
        var msg = "<html><body><h1>Hello server</h1>\n"
        val parms = session!!.parms
        msg += if (parms["username"] == null) {
            "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n"
        } else {
            "<p>Hello, " + parms["username"] + "!</p>"
        }
        return NanoHTTPD.newFixedLengthResponse("$msg</body></html>\n")
    }
}