package com.wittycode.poelevelinghelper.leveling.presentation

import com.wittycode.poelevelinghelper.leveling.model.LevelingStep
import com.wittycode.poelevelinghelper.leveling.model.toEntityModel
import com.wittycode.poelevelinghelper.leveling.model.toEntityModelWithPossiblePagedRels
import com.wittycode.poelevelinghelper.leveling.services.LevelingGuideAccessor
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.core.MediaType

@RestController
class LevelingController(
        private val levelingGuideAccessor: LevelingGuideAccessor
) {

    @RequestMapping("/leveling", produces = [MediaType.APPLICATION_JSON])
    fun fetchAllLeveling(): CollectionModel<EntityModel<LevelingStep>> {
        return CollectionModel(
                levelingGuideAccessor.enrichLevelingStepsWithGems().map{ it.toEntityModel() },
                linkTo(methodOn(LevelingController::class.java).fetchAllLeveling()).withSelfRel()
        )
    }

    @RequestMapping("/leveling/quest/{stepId}", produces = [MediaType.APPLICATION_JSON])
    fun fetchLevelingStep(@PathVariable stepId: Int): ResponseEntity<EntityModel<LevelingStep>> {
        return ResponseEntity(
                        levelingGuideAccessor.getStepById(stepId)
                                .toEntityModelWithPossiblePagedRels(levelingGuideAccessor)
                , HttpStatus.OK)
    }

    @RequestMapping("/leveling/act/{actId}", produces = [MediaType.APPLICATION_JSON])
    fun fetchLevelingStepsForAct(@PathVariable actId: Int): CollectionModel<EntityModel<LevelingStep>> {
        return CollectionModel(
                levelingGuideAccessor.getStepsByAct(actId).map{ it.toEntityModel() },
                linkTo(methodOn(LevelingController::class.java).fetchAllLeveling()).withSelfRel()
        )
    }
}