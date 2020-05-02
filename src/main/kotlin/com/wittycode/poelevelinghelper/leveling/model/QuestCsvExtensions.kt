package com.wittycode.poelevelinghelper.leveling.model

import com.opencsv.CSVReaderBuilder
import org.springframework.core.io.Resource
import java.io.BufferedReader
import java.io.InputStreamReader

fun Resource.loadQuests(): Set<Quest> {
    BufferedReader(InputStreamReader(this.inputStream)).use { reader ->
        val quests = mutableSetOf<Quest>()

        val csvReader = CSVReaderBuilder(reader)
                .build()

        val gemLines = csvReader.readAll()
        for (_record in gemLines) {
            quests.add(Quest(_record[0]))
        }
        return quests
    }
}