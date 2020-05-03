package com.wittycode.poelevelinghelper.gemimport.model


enum class AvailableClasses {
    MARAUDER,
    TEMPLAR,
    WITCH,
    SHADOW,
    RANGER,
    DUELIST,
    SCION
}

data class GemInfo(
        var gemName: String?,
        var vendor: String?,
        // default is Act 6 Lilly quest which unlocks all gems
        var questUnlock: String = "Fallen from Grace",
        var classUnlocks: MutableSet<AvailableClasses> = mutableSetOf()
) {
    constructor(gemName: String):
        this(gemName, "", "")

    fun isAvailableForClass(className: String): Boolean =
        classUnlocks.map { it.name }.contains(className.toUpperCase())

    fun isAvailableForQuest(questName: String?): Boolean =
            questUnlock.toUpperCase() == questName?.toUpperCase() ?: false

    fun resetToDefaultQuest() {
        this.questUnlock = "Fallen from Grace"
        this.vendor = "Act 6 Lilly"
    }
}