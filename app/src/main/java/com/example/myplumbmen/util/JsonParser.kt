package com.example.myplumbmen.util

import com.example.myplumbmen.data.LvLStructure
import org.json.JSONObject
import org.json.JSONException



class JsonParser {

    @Throws(JSONException::class)
    fun getLvl(response: String): LvLStructure {
        val lvlJson = JSONObject(response)
        val id = lvlJson.getInt("id")
        val matrixX = lvlJson.getInt("matrixX")
        val matrixY = lvlJson.getInt("matrixY")
//        val rawStartArray = userJson.getString("location")
//        var startArray = rawStartArray.removeSurrounding("[", "]").split(",").map { it.toInt() }.toIntArray()
        val startArray = arrayConverter(lvlJson.getString("startArray"))
        val answerArray = arrayConverter(lvlJson.getString("answerArray"))
        val endArray = arrayConverter(lvlJson.getString("endArray"))
        val backgroundArray = arrayConverter(lvlJson.getString("backgroundArray"))
        val background = lvlJson.getInt("background")

        return LvLStructure(id, matrixX, matrixY, startArray, answerArray , endArray ,backgroundArray, background)
    }

    private fun arrayConverter (stringToArray: String): IntArray {
        return stringToArray.removeSurrounding("[", "]").split(",").map { it.toInt() }.toIntArray()
    }
}