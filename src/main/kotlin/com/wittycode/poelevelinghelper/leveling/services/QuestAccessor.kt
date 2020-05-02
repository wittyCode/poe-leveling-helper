package com.wittycode.poelevelinghelper.leveling.services

import org.springframework.stereotype.Service

@Service
class QuestAccessor(private val questImporter: QuestImporter) {

    fun getAllQuests() = questImporter.quests
}