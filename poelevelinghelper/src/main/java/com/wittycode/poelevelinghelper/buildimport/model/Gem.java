package com.wittycode.poelevelinghelper.buildimport.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@JacksonXmlRootElement(localName = "Gem")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Gem {

    @JacksonXmlProperty(isAttribute = true, localName = "nameSpec")
    private String name;
}