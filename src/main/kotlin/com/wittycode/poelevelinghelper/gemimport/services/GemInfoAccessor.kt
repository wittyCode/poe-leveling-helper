package com.wittycode.poelevelinghelper.gemimport.services

import com.wittycode.poelevelinghelper.gemimport.model.AvailableClasses
import com.wittycode.poelevelinghelper.gemimport.model.GemInfo
import org.springframework.stereotype.Service

@Service
class GemInfoAccessor(private val gemImporter: GemImporter) {

    fun getAll(): List<GemInfo> = gemImporter.gemInfos

    fun getGemInfoByGemName(gemName: String): GemInfo {
        val gemsByName = gemImporter.gemInfos.associateBy { it.gemName }
        return when (gemsByName.containsKey(gemName)) {
            true -> gemsByName[gemName] ?: GemInfo(gemName = "Skillgem not found")
            else -> GemInfo(gemName = "Skillgem not found")
        }
    }

    fun getGemInfoByClassName(className: String): Set<GemInfo> {
        val classNameToCheck = className.toUpperCase()
        if (!AvailableClasses.values().map { it.name }.contains(classNameToCheck))
            return emptySet()
        val gemsByClass = gemImporter.gemInfos.filter { it.isAvailableForClass(classNameToCheck) }
        return gemsByClass.toSet()
    }

    fun getGemInfoByQuestName(questName: String): Set<GemInfo> =
            gemImporter.gemInfos.filter { it.isAvailableForQuest(questName) }.toSet()

}