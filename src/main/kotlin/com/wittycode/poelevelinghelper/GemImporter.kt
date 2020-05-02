package com.wittycode.poelevelinghelper

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import java.io.IOException
import java.nio.charset.StandardCharsets

@Service
class GemImporter(private val resourceLoader: ResourceLoader) {

    private val logger: Logger = LoggerFactory.getLogger(GemImporter::class.java)

    fun importGemInfo() {
        val resource = resourceLoader.getResource("classpath:gems.csv")
        try {
            val inputStream = resource.inputStream
            val bdata = FileCopyUtils.copyToByteArray(inputStream)
            val data = String(bdata, StandardCharsets.UTF_8)
            logger.info(data)
        } catch (e: IOException) {
            logger.error("IOException", e)
        }
        logger.info("gem import finished")
    }
}