package com.wittycode.poelevelinghelper.buildimport.services

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.wittycode.poelevelinghelper.buildimport.model.PoBContent
import com.wittycode.poelevelinghelper.buildimport.model.attachQuestUnlock
import com.wittycode.poelevelinghelper.gemimport.services.GemInfoAccessor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PobBuildAccessor(
        private val pobToXmlConverter: PobToXmlConverter,
        private val restTemplate: RestTemplate,
        private val gemInfoAccessor: GemInfoAccessor
) {
    private val logger = LoggerFactory.getLogger(PobBuildAccessor::class.java)

    final lateinit var poBContent: PoBContent
        private set

    fun loadPobBuild(pastebinUrl: String) {
        logger.info("Importing from Url {}", pastebinUrl)
        val rawPasteBinContent = restTemplate.getForObject(pastebinUrl, String::class.java)
        logger.info("Import finished, decompressing")
        val decompressedXmlFromPasteBin: String? = pobToXmlConverter.convertPobRawToXmlString(rawPasteBinContent)
        logger.info("Decompression finished, mapping XML")
        val xmlMapper = XmlMapper()
        this.poBContent = xmlMapper.readValue(decompressedXmlFromPasteBin, PoBContent::class.java)
        poBContent.skills.skills.attachQuestUnlock(gemInfoAccessor)
    }

    fun isPoBBuildImported(): Boolean = this::poBContent.isInitialized
}