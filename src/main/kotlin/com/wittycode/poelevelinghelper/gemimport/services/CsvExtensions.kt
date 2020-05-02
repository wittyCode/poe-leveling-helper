package com.wittycode.poelevelinghelper.gemimport.services

import com.opencsv.CSVReaderBuilder
import com.wittycode.poelevelinghelper.gemimport.model.AvailableClasses
import com.wittycode.poelevelinghelper.gemimport.model.GemInfo
import org.springframework.core.io.Resource
import java.io.BufferedReader
import java.io.InputStreamReader

fun String.equalsGemAvailablity(gemClasslabel: AvailableClasses): Boolean =
        this.toUpperCase() == gemClasslabel.name

fun Resource.parseGemsCsv(): List<GemInfo> {
    BufferedReader(InputStreamReader(this.inputStream)).use { reader ->
        // create csv bean reader

        val gemInfos = mutableListOf<GemInfo>()

        val csvReader = CSVReaderBuilder(reader).withSkipLines(1).build()

        val gemLines = csvReader.readAll()
        for (_record in gemLines) {
            val gemInfo = GemInfo(
                    _record[0],
                    _record[1],
                    _record[9],
                    mutableSetOf()
            )
            if (_record[2].equalsGemAvailablity(AvailableClasses.MARAUDER))
                gemInfo.classUnlocks.add(AvailableClasses.MARAUDER)
            if (_record[3].equalsGemAvailablity(AvailableClasses.TEMPLAR))
                gemInfo.classUnlocks.add(AvailableClasses.TEMPLAR)
            if (_record[4].equalsGemAvailablity(AvailableClasses.WITCH))
                gemInfo.classUnlocks.add(AvailableClasses.WITCH)
            if (_record[5].equalsGemAvailablity(AvailableClasses.SHADOW))
                gemInfo.classUnlocks.add(AvailableClasses.SHADOW)
            if (_record[6].equalsGemAvailablity(AvailableClasses.RANGER))
                gemInfo.classUnlocks.add(AvailableClasses.RANGER)
            if (_record[7].equalsGemAvailablity(AvailableClasses.DUELIST))
                gemInfo.classUnlocks.add(AvailableClasses.DUELIST)
            if (_record[8].equalsGemAvailablity(AvailableClasses.SCION))
                gemInfo.classUnlocks.add(AvailableClasses.SCION)
            gemInfos.add(gemInfo)
        }
        return gemInfos
    }
}