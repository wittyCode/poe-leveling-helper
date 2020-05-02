package com.wittycode.poelevelinghelper.leveling.services

import com.wittycode.poelevelinghelper.gemimport.services.GemInfoAccessor
import com.wittycode.poelevelinghelper.leveling.model.LevelingStep
import com.wittycode.poelevelinghelper.leveling.model.groupedById
import org.springframework.stereotype.Service

@Service
class LevelingGuideAccessor(
        private val levelingGuideImporter: LevelingGuideImporter,
        private val gemInfoAccessor: GemInfoAccessor
) {

    lateinit var enrichedQuestSteps: Set<LevelingStep>

    fun enrichLevelingStepsWithGems(): Set<LevelingStep> {
        val questSteps = levelingGuideImporter.levelingGuide.steps
        questSteps.forEach {
            val gems = gemInfoAccessor.getGemInfoByQuestName(it.quest ?: "Fallen from Grace")
            it.gems = gems.mapNotNull { gemInfo -> gemInfo.gemName }.toSet()
        }
        return questSteps
    }

    fun maxStepId(): Int = levelingGuideImporter.levelingGuide.steps.map { it.step }.max() ?: -1

    fun getStep(stepId: Int) : LevelingStep {
        if (!this::enrichedQuestSteps.isInitialized) {
            this.enrichedQuestSteps = this.enrichLevelingStepsWithGems()
        }
        return this.enrichedQuestSteps.groupedById()[stepId] ?: LevelingStep(-1, "", "")
    }
}