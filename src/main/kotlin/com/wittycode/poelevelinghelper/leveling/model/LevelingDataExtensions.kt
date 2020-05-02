package com.wittycode.poelevelinghelper.leveling.model

import com.wittycode.poelevelinghelper.leveling.presentation.LevelingController
import com.wittycode.poelevelinghelper.leveling.services.LevelingGuideAccessor
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder


fun LevelingStep.toEntityModel(): EntityModel<LevelingStep> =
        EntityModel(this, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LevelingController::class.java).fetchLevelingStep(this.step)).withSelfRel())

fun LevelingStep.toEntityModelWithPossibleNextRel(levelingGuideAccessor: LevelingGuideAccessor): EntityModel<LevelingStep> {
    val entityModel = EntityModel(this, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LevelingController::class.java).fetchLevelingStep(this.step)).withSelfRel())
    if (this.step < levelingGuideAccessor.maxStepId()) {
        entityModel.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LevelingController::class.java)
                        .fetchLevelingStep(this.step + 1)).withRel("next")
        )
    }
    return entityModel
}

fun Set<LevelingStep>.groupedById(): Map<Int, LevelingStep> = this.associateBy { it.step }