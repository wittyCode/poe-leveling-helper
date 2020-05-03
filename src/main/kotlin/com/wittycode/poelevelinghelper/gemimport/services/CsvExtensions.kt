package com.wittycode.poelevelinghelper.gemimport.services

import com.opencsv.CSVReaderBuilder
import com.wittycode.poelevelinghelper.gemimport.model.AvailableClasses
import com.wittycode.poelevelinghelper.gemimport.model.GemInfo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import java.io.BufferedReader
import java.io.InputStreamReader

fun Resource.parseGemsCsv(): List<GemInfo> {
    BufferedReader(InputStreamReader(this.inputStream)).use { reader ->
        val gemInfos = mutableListOf<GemInfo>()

        val csvReader = CSVReaderBuilder(reader)
                .withSkipLines(1)
                .build()

        val gemLines = csvReader.readAll()
        for (_record in gemLines) {
            val gemInfo = _record.gemGemInfoFromRecord()
            gemInfos.add(gemInfo)
        }
        return gemInfos
    }
}

fun Array<String>.gemGemInfoFromRecord(): GemInfo {
    val logger: Logger = LoggerFactory.getLogger(GemInfo::class.java)
    val gemInfo = GemInfo(
            this[0],
            this[1],
            this[9],
            mutableSetOf()
    )
    for (i in 2..8) {
        try {
            if (this[i].isNotEmpty()) {
                gemInfo.classUnlocks
                    .add(AvailableClasses.valueOf(
                        this[i].toUpperCase())
                    )
            }
        } catch (ex: IllegalArgumentException) {
            logger.error(ex.message)
        }
    }
    return gemInfo
}