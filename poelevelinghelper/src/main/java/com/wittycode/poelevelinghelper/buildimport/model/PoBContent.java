package com.wittycode.poelevelinghelper.buildimport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@JacksonXmlRootElement(localName = "PathOfBuilding")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PoBContent {
    
    @JacksonXmlProperty(localName = "Build")
    private Build build;

    @JacksonXmlProperty(localName = "Skills")
    private Skills skills;
}