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
        var questUnlock: String?,
        var classUnlocks: MutableSet<AvailableClasses> = mutableSetOf()
) {
    constructor(gemName: String):
        this(gemName, "", "")

    fun isAvailableForClass(className: String) :Boolean =
        classUnlocks.map { it.name }.contains(className.toUpperCase())

}

