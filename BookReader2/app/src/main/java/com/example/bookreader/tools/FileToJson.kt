package com.example.bookreader.tools

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File

object FileToJson {
    fun fileToJson(stringFile: File): JsonObject? {
        return JsonParser().parse(JsonToString.getJsonString(stringFile)).asJsonObject
    }
}