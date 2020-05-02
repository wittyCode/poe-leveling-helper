package com.wittycode.poelevelinghelper.buildimport.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement


@JacksonXmlRootElement(localName = "PathOfBuilding")
@JsonIgnoreProperties(ignoreUnknown = true)
class PoBContent {
        @set:JacksonXmlProperty(localName = "Build")
        var build: Build? = null


        @set:JacksonXmlProperty(localName = "Skills")
        var skills: Skills? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Build {
        @set:JacksonXmlProperty(isAttribute = true, localName = "bandit")
        var bandit: String = ""
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Skills {
        @set:[JacksonXmlElementWrapper(useWrapping = false) JacksonXmlProperty(localName = "Skill")]
        var skills: List<Skill?>? = null
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Skill {
        @set:JacksonXmlProperty(isAttribute = true, localName = "label")
        var label: String = ""

        @set:[JacksonXmlElementWrapper(useWrapping = false) JacksonXmlProperty(localName = "Gem")]
        var gems: List<Gem?>? = null
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Gem {
        @set:JacksonXmlProperty(isAttribute = true, localName = "nameSpec")
        var name: String = ""
}