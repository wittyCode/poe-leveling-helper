package com.wittycode.poelevelinghelper.leveling.services

import com.wittycode.poelevelinghelper.leveling.model.Quest
import com.wittycode.poelevelinghelper.leveling.model.loadQuests
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service

@Service
class QuestImporter(private val resourceLoader: ResourceLoader) {

    private val logger: Logger = LoggerFactory.getLogger(QuestImporter::class.java)

    final lateinit var quests: Set<Quest>
        private set

    fun importQuests() {
        val questCsvResource = resourceLoader.getResource("classpath:quests.csv")
        logger.info("Importing quests from csv finished")
        this.quests = questCsvResource.loadQuests()
    }
}