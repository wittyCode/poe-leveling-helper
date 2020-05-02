package com.wittycode.poelevelinghelper.gemimport.services

import com.wittycode.poelevelinghelper.gemimport.model.GemInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.io.IOException


@Service
class GemImporter(private val resourceLoader: ResourceLoader) {

    private val logger: Logger = LoggerFactory.getLogger(GemImporter::class.java)

    final lateinit var gemInfos: List<GemInfo>
        private set

    fun importGemInfo() {
        val gemFileResource = resourceLoader.getResource("classpath:gems.csv")
        try {
            gemInfos = gemFileResource.parseGemsCsv()
            logger.info("gem import finished")
        } catch (e: IOException) {
            logger.error("IOException", e)
        }
    }
}