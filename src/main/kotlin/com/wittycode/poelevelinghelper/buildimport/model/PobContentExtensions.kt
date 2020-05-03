package com.wittycode.poelevelinghelper.buildimport.model

import com.wittycode.poelevelinghelper.gemimport.services.GemInfoAccessor


fun List<Skill>.attachQuestUnlock(gemInfoAccessor: GemInfoAccessor) {
    this.forEach {
        it.gems.forEach {
            gem ->
            gem.questUnlock = gemInfoAccessor.getGemInfoByGemName(gem.name).questUnlock
        }
    }
}

fun PoBContent.getAllSkillNames() =
    this.skills.skills.flatMap { it.gems }.map { it.name }.toSet()
