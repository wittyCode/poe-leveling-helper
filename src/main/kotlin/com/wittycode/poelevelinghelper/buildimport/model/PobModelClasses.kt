package com.wittycode.poelevelinghelper.buildimport.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.wittycode.poelevelinghelper.gemimport.model.AvailableClasses


@JacksonXmlRootElement(localName = "PathOfBuilding")
@JsonIgnoreProperties(ignoreUnknown = true)
class PoBContent {
        @set:JacksonXmlProperty(localName = "Build")
        lateinit var build: Build

        @set:JacksonXmlProperty(localName = "Skills")
        lateinit var skills: Skills
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Build {
        @set:JacksonXmlProperty(isAttribute = true)
        lateinit var bandit: String
        @set:JacksonXmlProperty(isAttribute = true)
        lateinit var className: String

        fun getClassName(): AvailableClasses = AvailableClasses.valueOf(className.toUpperCase())
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Skills {
        @set:[JacksonXmlElementWrapper(useWrapping = false) JacksonXmlProperty(localName = "Skill")]
        lateinit var skills: List<Skill>
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Skill {
        @set:JacksonXmlProperty(isAttribute = true)
        lateinit var label: String

        @set:[JacksonXmlElementWrapper(useWrapping = false) JacksonXmlProperty(localName = "Gem")]
        var gems: List<Gem> = emptyList()
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Gem {
        @set:JacksonXmlProperty(isAttribute = true, localName = "nameSpec")
        lateinit var name: String

        @set:JacksonXmlProperty(isAttribute = true)
        lateinit var questUnlock: String
}