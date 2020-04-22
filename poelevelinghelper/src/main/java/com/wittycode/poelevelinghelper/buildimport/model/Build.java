package com.wittycode.poelevelinghelper.buildimport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Build {

    @JacksonXmlProperty(isAttribute = true)
    private String bandit;
}