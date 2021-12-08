package org.ctfcracktools.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.*

class SettingJson {
    private var settingFile = File("ctfcracktools_setting.json")
    init {
        if(!isJson()){
            val initSetting = mapOf("jython" to "")
            settingFile.createNewFile()
            writeJson(initSetting)
        }
    }

    /**
     * 返回一个储存配置信息的Map
     * @return Map<String,String>
     */
    fun parseJson():Map<String,String>{
        val settingReader = BufferedReader(FileReader(settingFile))
        return Gson().fromJson(settingReader, object : TypeToken<Map<String, String>>() {}.type)
    }

    /**
     * 将配置信息写入json文件
     * @param Setting String 配置信息
     */
    fun writeJson(setting:Map<String,String>){
        val gson = GsonBuilder().setPrettyPrinting().create()
        val writer = BufferedWriter(FileWriter(settingFile))
        writer.write(gson.toJson(setting))
        writer.flush()
    }
    fun isJson():Boolean = settingFile.isFile && settingFile.exists()
}