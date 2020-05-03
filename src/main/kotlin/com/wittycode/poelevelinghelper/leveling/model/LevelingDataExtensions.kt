package com.wittycode.poelevelinghelper.leveling.model

import com.wittycode.poelevelinghelper.buildimport.model.PoBContent
import com.wittycode.poelevelinghelper.buildimport.model.getAllSkillNames
import com.wittycode.poelevelinghelper.gemimport.model.GemInfo
import com.wittycode.poelevelinghelper.gemimport.model.filterForAvailableClass
import com.wittycode.poelevelinghelper.gemimport.model.filterForQuest
import com.wittycode.poelevelinghelper.gemimport.model.gemIsInBuild
import com.wittycode.poelevelinghelper.leveling.presentation.LevelingController
import com.wittycode.poelevelinghelper.leveling.services.LevelingGuideAccessor
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn


fun LevelingStep.toEntityModel(): EntityModel<LevelingStep> =
        EntityModel(this, linkTo(methodOn(LevelingController::class.java).fetchLevelingStep(this.step)).withSelfRel())

fun LevelingStep.toEntityModelWithPossiblePagedRels(levelingGuideAccessor: LevelingGuideAccessor): EntityModel<LevelingStep> {
    val entityModel = EntityModel(this, linkTo(methodOn(LevelingController::class.java).fetchLevelingStep(this.step)).withSelfRel())
    if (this.step < levelingGuideAccessor.maxStepId()) {
        entityModel.add(
                linkTo(methodOn(LevelingController::class.java)
                        .fetchLevelingStep(this.step + 1)).withRel("next")
        )
    }
    if (this.step > 1) {
        entityModel.add(
                linkTo(methodOn(LevelingController::class.java)
                        .fetchLevelingStep(this.step -1)).withRel("prev")
        )
    }
    return entityModel
}

fun Set<LevelingStep>.groupedById(): Map<Int, LevelingStep> = this.associateBy { it.step }


fun LevelingStep.enrichWithQuestGems(allGems: MutableSet<GemInfo>,poBContent: PoBContent) {
    val currentClass = poBContent.build.getClassName()
    // save all gems of this class in intermediary set to be able to reset all gems not available for this class
    val gemsOfThisClass = allGems.filterForAvailableClass(currentClass)
    // remove all gems that we do not want to reset to Act 6 availability
    allGems.removeIf { gem -> gemNameIsInOtherSet(gem.gemName, gemsOfThisClass) }

    allGems.forEach { gem ->
        gem.resetToDefaultQuest()
        gem.classUnlocks.add(currentClass)
    }
    // add gems that were not reset back to set of all gems so we have all quests filterable for this quest
    allGems.addAll(gemsOfThisClass)

    val gemsForThisQuest = this.quest?.let { allGems.filterForQuest(it) } ?: mutableSetOf()

    // since we now have every gem for this quest we need to check whether we need it in our build
    val gemsForQuestInBuild = gemsForThisQuest.filter { it.gemIsInBuild(poBContent.getAllSkillNames())}

    this.gems = gemsForQuestInBuild.mapNotNull { gemInfo -> gemInfo.gemName }.toSet()
}

fun gemNameIsInOtherSet(gemName: String?, otherGemSet: MutableSet<GemInfo>): Boolean {
    return otherGemSet.map { gem -> gem.gemName }.contains(gemName)
}

