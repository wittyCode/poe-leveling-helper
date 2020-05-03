package com.wittycode.poelevelinghelper.leveling.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class Quest(var name: String?)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LevelingGuide(var steps: Set<LevelingStep>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LevelingStep(var act: Int, var step: Int, var substeps: Set<LevelingSubStep>, var gems: Set<String> = mutableSetOf()) {

    var quest: String? = null
}

data class LevelingSubStep(var content: String)
