package com.wittycode.poelevelinghelper.buildimport.presentation

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.wittycode.poelevelinghelper.buildimport.model.PoBContent
import com.wittycode.poelevelinghelper.buildimport.services.domain.PobToXmlConverter
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.io.IOException


@RestController
class PobImportController(private val restTemplate: RestTemplate, private val pobToXmlConverter: PobToXmlConverter) {

    private val logger = LoggerFactory.getLogger(PobImportController::class.java)

    @RequestMapping("/import/pob")
    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    fun importPoBFromPastebin(
            @RequestParam("pastebinurl") pasteBinUrl: String
    ): PoBContent {
        logger.info("Importing from Url {}", pasteBinUrl)
        val rawPasteBinContent = restTemplate.getForObject(pasteBinUrl, String::class.java)
        logger.info("Import finished, decompressing")
        val decompressedXmlFromPasteBin: String? = pobToXmlConverter.convertPobRawToXmlString(rawPasteBinContent)
        logger.info("Decompression finished, mapping XML")
        val xmlMapper = XmlMapper()
        return xmlMapper.readValue(decompressedXmlFromPasteBin, PoBContent::class.java)
    }
}