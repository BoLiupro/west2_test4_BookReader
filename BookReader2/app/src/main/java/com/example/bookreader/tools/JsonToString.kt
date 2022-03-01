package com.example.bookreader.tools

import com.google.gson.JsonObject
import java.io.*

object JsonToString {
    fun getJsonString(file: File?): String {
        val stringBuilder = StringBuilder()
        try {
            val bf = BufferedReader(
                InputStreamReader(
                    FileInputStream(file)
                )
            )
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        
        return stringBuilder.toString().replace("\\\"","\"").replace("\\/", "/")
    }

    fun getJsonString(json: JsonObject): String {
        return json.toString().replace("\\\"","\"").replace("\\/", "/")
    }
}