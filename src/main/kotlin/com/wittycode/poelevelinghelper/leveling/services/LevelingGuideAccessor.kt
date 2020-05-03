package com.wittycode.poelevelinghelper.leveling.services

import com.wittycode.poelevelinghelper.buildimport.services.PobBuildAccessor
import com.wittycode.poelevelinghelper.gemimport.services.GemInfoAccessor
import com.wittycode.poelevelinghelper.leveling.model.LevelingStep
import com.wittycode.poelevelinghelper.leveling.model.enrichWithQuestGems
import com.wittycode.poelevelinghelper.leveling.model.groupedById
import org.springframework.stereotype.Service

@Service
class LevelingGuideAccessor(
        private val levelingGuideImporter: LevelingGuideImporter,
        private val gemInfoAccessor: GemInfoAccessor,
        private val pobBuildAccessor: PobBuildAccessor
) {

    lateinit var enrichedQuestSteps: Set<LevelingStep>

    fun enrichLevelingStepsWithGems(): Set<LevelingStep> {
        val questSteps = levelingGuideImporter.levelingGuide.steps
        if (pobBuildAccessor.isPoBBuildImported()) {
            val poBContent = pobBuildAccessor.poBContent
            questSteps.forEach {
                it.enrichWithQuestGems(gemInfoAccessor.getAll(), poBContent)
            }
            enrichedQuestSteps = questSteps
        }
        return questSteps
    }

    fun maxStepId(): Int = levelingGuideImporter.levelingGuide.steps.map { it.step }.max() ?: -1

    fun getStepById(stepId: Int): LevelingStep {
        if (!this::enrichedQuestSteps.isInitialized) {
            this.enrichedQuestSteps = this.enrichLevelingStepsWithGems()
        }
        return this.enrichedQuestSteps.groupedById()[stepId] ?: LevelingStep(-1, "")
    }

}