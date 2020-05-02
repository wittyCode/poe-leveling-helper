package com.wittycode.poelevelinghelper.leveling.services

import com.google.gson.Gson
import com.wittycode.poelevelinghelper.leveling.model.LevelingGuide
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
class LevelingGuideImporter(val resourceLoader: ResourceLoader) {

    private val logger: Logger = LoggerFactory.getLogger(LevelingGuideImporter::class.java)

    final lateinit var levelingGuide: LevelingGuide
        private set

    fun importLevelingGuide() {
        val levelingJson = resourceLoader.getResource("classpath:leveling.json")
        BufferedReader(InputStreamReader(levelingJson.inputStream)).use { reader ->
            val jsonString = reader.readText()
            val gson = Gson()
            this.levelingGuide = gson.fromJson(jsonString, LevelingGuide::class.java)
            logger.info("Importing leveling guide from json finished")
            logger.info(this.levelingGuide.toString())
        }
    }
}