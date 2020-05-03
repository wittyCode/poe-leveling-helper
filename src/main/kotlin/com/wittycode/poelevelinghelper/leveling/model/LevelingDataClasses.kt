package com.wittycode.poelevelinghelper.leveling.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class Quest(var name: String?)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LevelingGuide(var steps: Set<LevelingStep>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LevelingStep(var step: Int, var content: String, var gems: Set<String> = mutableSetOf()) {

    var quest: String? = null
}
