package com.wittycode.poelevelinghelper.gemimport.model

fun  MutableSet<GemInfo>.filterForAvailableClass(availableClasses: AvailableClasses): MutableSet<GemInfo> =
        this.filter { it.isAvailableForClass(availableClasses.name) }.toMutableSet()


fun MutableSet<GemInfo>.filterForQuest(quest: String) =
        this.filter { it.isAvailableForQuest(quest) }.toMutableSet()


fun GemInfo.gemIsInBuild(allSkillNames: Set<String>): Boolean =
        allSkillNames.contains(this.gemName)
